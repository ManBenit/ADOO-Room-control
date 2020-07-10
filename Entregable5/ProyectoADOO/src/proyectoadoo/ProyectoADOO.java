package proyectoadoo;

import interfaz.Inicio;
import interfaz.MenuAdmin;
import interfaz.MenuNoAdmin;


/*

Acceso al Panel del host
adooroomcontrol.byethost22.com
Usuario: b22_26029529.
Contraseña: adooroomcontrol.
*/

public class ProyectoADOO {

    public static void main(String[] args) {
        ServicioWeb ws= ServicioWeb.obtenerInstancia("GET");
        Inicio nu= new Inicio(ws);
        nu.setVisible(true);
    }
}