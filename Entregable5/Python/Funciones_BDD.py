from Conexion import Conexion
from datetime import datetime

conexion= Conexion.obtenerConexion(Conexion)

class Funciones_BDD:
    def modEdoAct(self, tem, hum, luz, luzC, humo, pre, son, ventTem, ventHum, ventHumo):
        try:
            with conexion.cursor() as cursor:
                self.__agregarDeteccion(self, pre, humo, son, 0, None)
                query= "CALL actualizarEstadoActual("+str(tem)+", "+str(hum)+", "+str(luz)+", '"+str(luzC)+"', "+str(humo)+", "+str(pre)+", "+str(son)+", "+str(ventTem)+", "+str(ventHum)+", "+str(ventHumo)+")"
                cursor.execute(query)
            conexion.commit()
        except conexion.Error as error:
            print("Error al realizar conexión.\n{}".format(error))

    def modEdoActNoLuz(self, tem, hum, humo, pre, son, ventTem, ventHum, ventHumo):
        try:
            with conexion.cursor() as cursor:
                self.__agregarDeteccion(self, pre, humo, son, 1, [tem, hum])
                query= "CALL actualizarEstadoActualNoLuz("+str(tem)+", "+str(hum)+", "+str(humo)+", "+str(pre)+", "+str(son)+", "+str(ventTem)+", "+str(ventHum)+", "+str(ventHumo)+")"
                cursor.execute(query)
            conexion.commit()
        except conexion.Error as error:
            print("Error al realizar conexión.\n{}".format(error))

        
    def obtEdoAct(self):
        try:
            with conexion.cursor() as cursor:
                sql = "SELECT * FROM estadoActual"
                cursor.execute(sql)
                result = cursor.fetchall()
                return result
        except conexion.Error as error:
            print("Error al realizar conexión.\n{}".format(error))
    
    def obtCaracLuz(self):
        try:
            with conexion.cursor() as cursor:
                sql = "SELECT IntenCaracter FROM LED WHERE IdLed=(SELECT MAX(IdLed) FROM LED)"
                cursor.execute(sql)
                result = cursor.fetchall()
                return result
        except conexion.Error as error:
            print("Error al realizar conexión.\n{}".format(error))



    def __selectMaxId(self, columna, tabla, idTabla):
        try:
            with conexion.cursor() as cursor:
                sql = "SELECT "+str(columna)+" FROM "+str(tabla)+" WHERE "+str(idTabla)+"=(SELECT MAX("+str(idTabla)+") FROM "+str(tabla)+")"
                cursor.execute(sql)
                result = cursor.fetchall()
                return result
        except conexion.Error as error:
            print("Error al realizar conexión.\n{}".format(error))

    def __insertGenerico(self, tabla, valores):
        try:

            with conexion.cursor() as cursor:
                query= "INSERT INTO "+str(tabla)+" VALUES"+self.__hacerListaDeValores(self, valores);
                cursor.execute(query)
            conexion.commit()
        except conexion.Error as error:
            print("Error al realizar conexión.\n{}".format(error))

    #Recibe una lista de valores y retorna esos valores en formato SQL para insertarlos
    def __hacerListaDeValores(self, arregloValores):
        vals="'"
        for i in arregloValores:
            vals+= str(i)+"','"
        vAux= list(vals)

        vals=""
        for i in range(0, len(vAux)-2):
            vals+= vAux[i]

        return "("+vals+")"

     #Este método agrega detección, en caso de haber, de sonido, humo o presencia
    def __agregarDeteccion(self, pre, humo, son, bndTempHum:int, temphum):
        fecha= "{}".format(datetime.now().date())
        hora= datetime.now().strftime("%H:%M:%S")

        ultimaPre= self.__selectMaxId(self, "*", "SensorPIR", "IdPir")
        if str(ultimaPre[0][1])!=str(pre): #Si lo detectado es diferente a lo que está registrado
            lista= list(ultimaPre[0])
            lista[0]+=1
            lista[2]= fecha
            lista[3]= hora
            self.__insertGenerico(self, "SensorPIR", lista)

        ultimoHumo= self.__selectMaxId(self, "*", "SensorHumo", "IdHumo")
        if str(ultimoHumo[0][1])!=str(humo): #Si lo detectado es diferente a lo que está registrado
            lista= list(ultimoHumo[0])
            lista[0]+=1
            lista[2]= fecha
            lista[3]= hora
            self.__insertGenerico(self, "SensorHumo", lista)

        ultimoSon= self.__selectMaxId(self, "*", "SensorSonido", "IdSon")
        if str(ultimoSon[0][1])!=str(son): #Si lo detectado es diferente a lo que está registrado
            lista= list(ultimoSon[0])
            lista[0]+=1
            lista[2]= fecha
            lista[3]= hora
            self.__insertGenerico(self, "SensorSonido", lista)

        if bndTempHum==1:
            ultimaTH= self.__selectMaxId(self, "*", "TempHum", "IdTH")
            if  int(temphum[0])>0 and int(temphum[1])>0 and int(ultimaTH[0][1])>0 and int(ultimaTH[0][2])>0:
                lista= list(ultimaTH[0])
                lista[0]+=1
                lista[3]= fecha
                lista[4]= hora
                self.__insertGenerico(self, "TempHum", lista)

                """str(ultimaTH[0][1])!=str(temphum[0]) and str(ultimaTH[0][2])!=str(temphum[1]) and"""

   
