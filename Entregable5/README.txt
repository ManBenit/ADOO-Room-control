-Inicie su servidor local (Ampps, Xampp, Wampp, etc.).

-Instale e inicie el servidor Tomcat en su computadora.El arranque y enlace puede ser más fácil con el IDE NetBeans.

-Copie y pegue la carpeta Room-control en su carpeta de servidor (comúnmente htdocs o www).

-Cree una base de datos llamada "Room-control" con ayuda de su servidor local e importe el archivo ADOO_BDD_V3.sql,
posteriormente entre a la base de datos y pegue el contenido de Procedures.txt, con esto generará los registros iniciales y 
laas vistas y procedimientos que utiliza el código.

-Arme el circuito indicado y con ayuda del IDE de arduino (instalar si es necesario) abra y cargue el código a la placa.

-Ejecute el archivo ComunicacionCircuito.py de la siguiente forma: python ComunicacionCircuito.py.
El script es compatible con versiones 3.X de python y debe instalar los paquetes que hagan falta (pymysql, serial).

-Finalmente, ejecute el archivo principal ProyectoADOO.java con ayuda de netbeans para poder entrar a la aplicación
de escritorio.


Nota: La placa arduino debe estar conectada al puerto serial (USB) de su computador.


