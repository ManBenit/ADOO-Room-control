//#include <MySQL_Connection.h>
//#include <MySQL_Cursor.h>

//Valores
int presencia=0, humo=0, sonido=0, ventilador=0, usuario=0; //ingresado con switch
int luz=0, luzLDR=0; //leído por base de datos //leído del serial
int humedad=10; //leído del serial //constante
int temperatura; //leído
char* resultados;

//Sensores //Pines de entrada
const int senPresencia= 6;
const int senHumo= 5;
const int senSonido= 4;
const int edoVentilador= 3;
const int sigUsuario= 2;
//const int senLuz= 9;
//const int senHumedad= 6; //mismo sensor
const int senTemperatura= A0; //mismo sensor //en la prueba está en °F, en el código java se convertirá a celsius
const int senLDR=A1; 

//Salidas
const int salLuz=9;
const int salVent=7;
const int senUsuario=8;

void setup() {
  Serial.begin(9600);
  
  pinMode(senPresencia, INPUT);
  pinMode(senHumo, INPUT);
  pinMode(senSonido, INPUT);
  pinMode(edoVentilador, INPUT);
  pinMode(sigUsuario, INPUT);
  //pinMode(senTemperatura, INPUT);

  pinMode(salLuz, OUTPUT);
  pinMode(salVent, OUTPUT);
  pinMode(senUsuario, OUTPUT);
}

void loop() {
 // resultados= "";
  
  //char* elchar= Serial.read();
  //int tempLeida= analogRead(senTemperatura);
  temperatura= analogRead(senTemperatura);
  //strcat(resultados, temperatura);
  luzLDR= analogRead(senLDR);

  luz= cambiarIntensidadLuminosa();
  analogWrite(salLuz, luz);

  presencia= digitalRead(senPresencia);
  humo= digitalRead(senHumo);
  sonido= digitalRead(senSonido);
  ventilador= digitalRead(edoVentilador);
  if(ventilador==1)
    digitalWrite(salVent, HIGH);
  else
    digitalWrite(salVent, LOW);

  usuario= digitalRead(sigUsuario);
  if(usuario==1)
    digitalWrite(senUsuario, HIGH);
  else
    digitalWrite(senUsuario, LOW);

  imprimirInfo(&presencia, &humo, &sonido, &ventilador, &usuario, &luz, &luzLDR, &humedad, &temperatura);
  
  delay(2000);
}

void imprimirInfo(int*presencia, int*humo, int*sonido, int*ventilador, int*usuario, int*luz, int*luzLDR, int*humedad, int*temperatura){
  Serial.print("Presencia:");
  Serial.print(*presencia);
  Serial.print("\n");

  Serial.print("Humo:");
  Serial.print(*humo);
  Serial.print("\n");

  Serial.print("Sonido:");
  Serial.print(*sonido);
  Serial.print("\n");

  Serial.print("Ventilador:");
  Serial.print(*ventilador);
  Serial.print("\n");

  Serial.print("Usuario:");
  Serial.print(*usuario);
  Serial.print("\n");

  Serial.print("Luz:");
  Serial.print(*luz);
  Serial.print("\n");

  Serial.print("LuzLDR:");
  Serial.print(*luzLDR);
  Serial.print("\n");

  Serial.print("Humedad:");
  Serial.print(*humedad);
  Serial.print("\n");

  Serial.print("Temperatura:");
  Serial.print(*temperatura);
  Serial.print("\n");

  Serial.print("\n");
  Serial.print("\n");
  Serial.print("\n");
}

int cambiarIntensidadLuminosa(){
  char caso = Serial.read();
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
      luz=180;
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









