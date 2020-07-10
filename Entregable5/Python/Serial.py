import sys
import glob
import serial

class Serial:
    def __init__(self):
        self.listaPuertos=[]
    
    def obtenerlistaDePuertos(self):
        return self.listaPuertos

    @staticmethod
    def obtenerPuertos(self):
        if sys.platform.startswith("win"):
            ports = ["COM%s" % (i + 1) for i in range(256)]
        elif sys.platform.startswith("linux") or sys.platform.startswith("cygwin"):
            # this excludes your current terminal "/dev/tty"
            ports = glob.glob("/dev/tty[A-Za-z]*")
        elif sys.platform.startswith("darwin"):
            ports = glob.glob("/dev/tty.*")
        else:
            raise EnvironmentError("Unsupported platform")

        result = []
        for port in ports:
            try:
                s = serial.Serial(port)
                s.close()
                result.append(port)
            except (OSError, serial.SerialException):
                pass

        return result

    @staticmethod
    def conectarPuerto(self, numPuerto, listaDePuertos, baudios):
        return serial.Serial(listaDePuertos[numPuerto], baudios)
        

