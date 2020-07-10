import pymysql.cursors

class Conexion:
    @staticmethod
    def obtenerConexion(self):
        # Connect to the database
        conexion = pymysql.connect(
            host='localhost',
            user='cjmebm98sh',
            password='cejotashuriken',
            db='Room_control'
            #charset='utf8mb4',
            #cursorclass=pymysql.cursors.DictCursor
        )

        return conexion
