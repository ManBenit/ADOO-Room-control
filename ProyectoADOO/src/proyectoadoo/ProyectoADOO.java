package proyectoadoo;

import herramientas.Fecha;
import herramientas.GeneradorDeUsuario;
import herramientas.ServicioWeb;


/*
Acceso al Panel del host
adooroomcontrol.byethost22.com
Usuario: b22_26029529.
Contraseña: adooroomcontrol.
*/

public class ProyectoADOO {

    public static void main(String[] args) {
//        ElMenu g= new ElMenu();
//        g.setVisible(true);
//        Login v= new Login();
//        v.setVisible(true);

        
        ServicioWeb ws= ServicioWeb.obtenerInstancia("GET");
        
//        //Nuevos usuarios
////        ws.nuevoUsuario("CÉSAR IVÁN", "HERNÁNDEZ", "PACHECO", "otracontra", true);
////        ws.nuevoUsuario("MANUEL EMILIO", "BENÍTEZ", "MORALES", "shuriken", true);
////        ws.nuevoUsuario("MARISOL", "MUÑOZ", "LÓPEZ", "cocoa2109", false);
////        ws.nuevoUsuario("IVÁN", "GARCÍA", "GARCÍA CANO", "ivannavi", false);
////        ws.nuevoUsuario("GABRIELA", "MORALES", "FLEMATE", "gaviota", false);
//        System.out.println(ws.peticionDeRespuesta());
        

//        //Eliminar usuarios
//        String eliminar= "1PHPE344-346PIGN1"; 
//        ws.eliminarUsuario(eliminar);
//        System.out.println(ws.peticionDeRespuesta());


//        //Pedir usuarios
//        System.out.println(ws.recibirListaUsuarios());

//        //Pedir estado actual
//        System.out.println(ws.recibirEstadoActual());

//        //Enviar porcentaje de luz
//        ws.enviarLuz("100");
//        System.out.println(ws.peticionDeRespuesta());

//        //Enviar señal al ventilador
//        ws.enviarSenalVentilador("0");
//        System.out.println(ws.peticionDeRespuesta());
    }
}