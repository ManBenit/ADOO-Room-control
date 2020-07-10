//Valores
int presencia=0, ventilador=0;
int usuario= 0;
String datosUsuario="";
char lectura;
bool ctrlUsuario=false;
int humo=0, humoVal=0, sonido=0, sonidoVal=0;
int luz=0, luzLDR=0; //leído por base de datos //leído del serial
int humedad=0; //constante
int temperatura=0.0; //leído del sensor

//Sensores //Pines de entrada
const int senPresencia= 6;
const int senHumo= A2;
const int senSonido= A3;

//const int senHumedad= 6; //mismo sensor
const int senTemperatura= A0; //mismo sensor //en la prueba está en °F, en el código java se convertirá a celsius
const int senLDR=A1; 

//Salidas
const int salLuz=9;
const int salVent=7;
const int senUsuario=8;

String cadenaValores = "";
char formato[] = "luz*ventilador*presencia*humo*sonido";

void setup() {
  Serial.begin(9600);
  
  pinMode(senPresencia, INPUT);
  pinMode(senHumo, INPUT);
  pinMode(senSonido, INPUT);

  pinMode(salLuz, OUTPUT);
  pinMode(salVent, OUTPUT);
  pinMode(senUsuario, OUTPUT);
}

void loop() {
  leerSensores();
  leerCtrlUsuario();
  
  if(ctrlUsuario){
    digitalWrite(senUsuario, HIGH);
    datosUsuario= Serial.readStringUntil('\r\n');
    int lon= datosUsuario.length()+1;
    char luzvent[lon]; //[0]=luz, [1]= ventilador: 0=apagado, 1=encendido
    datosUsuario.toCharArray(luzvent, lon);
    if(luzvent[1]=='1'){
      ventilador=1;
      digitalWrite(salVent, HIGH);
    }
    else{
      ventilador=0;
      digitalWrite(salVent, LOW);
    }

    luz= variacionDeLuzUsuario(luzvent[0]);
    analogWrite(salLuz, luz);
  }
  else{
    digitalWrite(senUsuario, LOW);
    if(temperatura>=26 || humedad>=30 || humo==1){
      ventilador=1;
      digitalWrite(salVent, HIGH);
    }
    else{
      ventilador=0;
      digitalWrite(salVent, LOW);
    }
  
    if(presencia==1)
      luz= variacionDeLuzCircuito();
    else
      luz=0;    
    analogWrite(salLuz, luz);
  }

  Serial.println(enviarResultados());
  
  delay(1000);
}

void leerSensores(){
  temperatura= obtenerTemperatura(analogRead(senTemperatura));
  humedad=10;
  luzLDR= analogRead(senLDR);
  sonidoVal=analogRead(senSonido);
  humoVal= analogRead(senHumo);
  presencia= alertaPresencia();
  //
  sonido= alertaSonido();
  humo= alertaHumo();
  
}

void leerCtrlUsuario(){
  lectura=Serial.read();
  if(lectura!=-1 && !ctrlUsuario){
    if(lectura=='u'){ //Señal de control de ususario
      datosUsuario="";
      ctrlUsuario=true;
    }
  }
  else if(lectura!=-1 && ctrlUsuario){
    if(lectura=='-'){ //Señal de control de ususario
      datosUsuario="";
      ctrlUsuario=false;
    }
  }
}

//Control de circuito
int variacionDeLuzCircuito(){
  if(luzLDR>=600)
    return 255;
  else{
    if(luzLDR>=0 && luzLDR<60)
      return 0;
    else if(luzLDR>=60 && luzLDR<120)
      return 26;
    else if(luzLDR>=120 && luzLDR<180)
      return 51;
    else if(luzLDR>=180 && luzLDR<240)
      return 77;
    else if(luzLDR>=240 && luzLDR<300)
      return 102;
    else if(luzLDR>=300 && luzLDR<360)
      return 180;
    else if(luzLDR>=360 && luzLDR<420)
      return 153;
    else if(luzLDR>=420 && luzLDR<480)
      return 179;
    else if(luzLDR>=480 && luzLDR<540)
      return 204;
    else if(luzLDR>=540 && luzLDR<600)
      return 230;
  }
}

//Control de usuario
int variacionDeLuzUsuario(char caso){
  switch (caso){
    case 'z':
      luz=0;
      break;
    
    case 'd':
      luz=26;
      //luz=10;
      break;
    
    case 'v':
      luz=51;
      //luz=20;
      break;
    
    case 't':
      luz=77;
      //luz=30;
      break;
    
    case 'c':
      luz=102;
      //luz=40;
      break;
    
    case 's':
      luz=127;
      //luz=50;
      break;
    
    case 'x':
      luz=153;
      //luz=60;
      break;
    
    case 'r':
      luz=179;
      //luz=70;
      break;
    
    case 'o':
      luz=204;
      //luz=80;
      break;
    
    case 'n':
      luz=230;
      //luz=90;
      break;
    
    case 'a':
      luz=255;
      //luz=100;
      break;

    default:
      luz=luz;
      break;
  }
  
  return luz;
}

int alertaSonido(){
  //El sonido el aceptable a menos de 50dB (simulador en 500), arriba es sonido fuerte
  if(sonidoVal>750)
    return 1; //Alerta de sonido fuerte
  else
    return 0;
}

int alertaHumo(){
  //El sonido el estable a menos de 10ppm (simulador en 100), arriba es humo tóxico
  if(humoVal>350)
    return 1; //Alerta de sonido fuerte
  else
    return 0;
}

int alertaPresencia(){
  if(digitalRead(senPresencia)==HIGH)
    return 1;
  else
    return 0;
}

/*
Por la entrada de arduino, con 1024 posibles valores, 
si tenemos 0V a la entrada nos devolverá 0 y si tenemos 5V nos devolverá 1023.
1°C - 10mV => 500°C - 5V => 500°C - 1023 
*/
int obtenerTemperatura(int lecturaSensor){
  return (500*lecturaSensor)/1023;
}


String enviarResultados(){
  String resultados="";
  resultados.concat(ctrlUsuario);
  resultados.concat("@");
  resultados.concat(temperatura);
  resultados.concat("@");
  resultados.concat(humedad);
  resultados.concat("@");
  resultados.concat(luz);
  resultados.concat("@");
  resultados.concat(humo);
  resultados.concat("@");
  resultados.concat(presencia);
  resultados.concat("@");
  resultados.concat(sonido);
  resultados.concat("@");
  resultados.concat(ventilador);

  return resultados;
}


