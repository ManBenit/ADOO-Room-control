from Serial import Serial
from Funciones_BDD import Funciones_BDD
from threading import Thread,Semaphore
import time
 
semaforo = Semaphore(1) #controlador de comunicación


def porcentajeDeLuz(luz:int):
    if luz==0:
        return [0, 'z']
    elif luz==26:
        return [10, 'd']
    elif luz==51:
        return [20, 'v']
    elif luz==77:
        return [30, 't']
    elif luz==102:
        return [40, 'c']
    elif luz==127:
        return [50, 's']
    elif luz==153:
        return [60, 'x']
    elif luz==179:
        return [70, 'r']
    elif luz==204:
        return [80, 'o']
    elif luz==230:
        return [90, 'n']
    elif luz==255:
        return [100, 'a']
    else:
        return [0, 'z']

def aceptarCadena(cadenaRecibida):
    for i in cadenaRecibida:
        if i=="":
            cadenaRecibida.remove(i)
    
    return cadenaRecibida

####################### C O N T R O L  U S U A R I O / C I R C U I T O  #######################
def actualizarEdoAct(listaValores): #Control de circuito
    # usuario, temp, hum, luz, humo, pres, son, vent
    ventT= 0 #Temp
    ventH= 0 #Humedad
    ventHu= 0 #Humo
    if int(listaValores[7])!=0:
        if int(listaValores[1])>=26:
            ventT= 1
        if int(listaValores[2])>=30:
            ventH= 1
        if int(listaValores[4])==1:
            ventHu=1

    luz= porcentajeDeLuz(int(listaValores[3]))

    if int(listaValores[0])==0: #Si no hay señal de usuario, que el circuito meta su valor
        Funciones_BDD.modEdoAct(Funciones_BDD, 
        listaValores[1], listaValores[2], luz[0], luz[1], listaValores[4], listaValores[5], listaValores[6],
        ventT, ventH, ventHu)
    else: #si no, modifica las cosas excepto la luz
        Funciones_BDD.modEdoActNoLuz(Funciones_BDD, 
        listaValores[1], listaValores[2], listaValores[4], listaValores[5], listaValores[6],
        ventT, ventH, ventHu)


def cambiarLuzVent(): #Control de usuario
    estadoActual= Funciones_BDD.obtEdoAct(Funciones_BDD)
    time.sleep(0.5)
    ctrlU=""
    luz=""
    ventilador=""

    control= ( estadoActual[0][len(estadoActual[0])-1] ).split("*")
    if control[len(control)-1]=='-':
        ctrlU= "-"
    else:
        ctrlU= "u"
        ventilador= control[len(control)-1]
        luz= Funciones_BDD.obtCaracLuz(Funciones_BDD)

    if ctrlU=="-":
        return ctrlU
    else:
        return ctrlU+str(luz[0][0])+str(ventilador)
###############################################################################################


####################### L E C T U R A  Y  E S C R I T U R A ###################################
def lecturaSerial(puerto):
    buffer=""
    while True:
        oneByte= puerto.read(1)
        if oneByte==b"\r":
            break
        else:
            buffer += oneByte.decode("ascii")
    actualizarEdoAct(buffer.split("@"))


def escrituraSerial(puerto):
    control= cambiarLuzVent()
    puerto.write(control.encode())

###############################################################################################



####################### S E M Á F O R O  C O N T R O L A D O R ################################
def regionCritica(bnd, pto):
    #global puerto
    lecturaSerial(pto)
    while True:
        if bnd:
            lecturaSerial(pto)
            bnd=False
        else:
            escrituraSerial(pto)
            bnd=True

#Definicion de Clase Hilo
class Hilo(Thread):
    def __init__(self, bnd, pto): #Constructor de la clase
        Thread.__init__(self)
        self.bnd= bnd
        self.pto= pto
 
    def run(self): #Metodo que se ejecutara con la llamada start
        semaforo.acquire()
        regionCritica(self.bnd, self.pto)
        semaforo.release()
###############################################################################################





if __name__=='__main__':
    ptoSerie= Serial.obtenerPuertos(Serial)

    #Encontrar un puerto serie disponible, no arranca hasta que encuentre uno
    if len(ptoSerie)==0:
        print("Buscando puerto...")
        while len(ptoSerie)==0:
            ptoSerie= Serial.obtenerPuertos(Serial)
            #Quédate quieto, esperando
    
    print("Puertos disponibles:")
    for i in range(0, len(ptoSerie)):
        print(str(i+1)+". "+ptoSerie[i])
    
    indice= int(input("Usar "))
    puerto= Serial.conectarPuerto(Serial, indice-1, ptoSerie, 9600)
    print()

    
    #Programa Principal
    hilo1= Hilo(True, puerto) #Creacion de objetos Hilos
    hilo2= Hilo(False, puerto)

    hilo2.start()
    hilo1.start() #Ejecutar todos los hilos
    



    




        
    

    





