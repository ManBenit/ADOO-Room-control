package proyectoadoo;

import herramientas.Mensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class ServicioWeb {
    private String metodo;
    private StringBuilder urlParaVisitar;
    
    private ServicioWeb(String metodo){
        this.metodo= metodo;
    }
    
    public static ServicioWeb obtenerInstancia(String metodo){
        return new ServicioWeb(metodo);
    }
    
    
    public String peticionDeRespuesta(){
        StringBuilder resultado= new StringBuilder("");
        try{
            URL url= new URL(obtenerURL());
            
            // Abrir la conexión
            HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod(metodo);
            
            // Búfer de lectura
            BufferedReader rd= new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            
            String linea;
            while ((linea= rd.readLine()) != null)
                resultado.append(linea);

            rd.close();
        }catch(IOException ex){
            new Mensaje().mostrarMensaje(Mensaje.ERROR, ex.toString());
        }
        
        return resultado.toString();
    }
    
    public void nuevoUsuario(String idUsuario, String nombre, String apaterno, String amaterno, String fecha, String contra, boolean admin){
        definirURL("http://localhost/Room-control/nuevoUsuario.php");
        int ad=0;
        if(admin) ad=1;
        //String fecha= new Fecha("yyyy-MM-dd").getSFecha();
        //String idUsuario= new GeneradorDeUsuario(nombre, apaterno, amaterno, fecha).obtenerUsuario();
        //Codificación de datos
        try{
            urlParaVisitar.append("?");
            urlParaVisitar.append("idUs=").append(idUsuario).append("&");
            urlParaVisitar.append("nom=").append(URLEncoder.encode(nombre, "utf-8")).append("&");
            urlParaVisitar.append("apa=").append(URLEncoder.encode(apaterno, "utf-8")).append("&");
            urlParaVisitar.append("ama=").append(URLEncoder.encode(amaterno, "utf-8")).append("&");
            urlParaVisitar.append("contra=").append(URLEncoder.encode(contra, "utf-8")).append("&");
            urlParaVisitar.append("fecha=").append(URLEncoder.encode(fecha, "utf-8")).append("&");
            urlParaVisitar.append("admin=").append(URLEncoder.encode(String.valueOf(ad), "utf-8"));
        }catch(UnsupportedEncodingException uee){
            new Mensaje().mostrarMensaje(Mensaje.ERROR, uee.toString());
        }
        
        enviarDatos();
    }
    
    public void eliminarUsuario(String idStr){
        String[] ids= idStr.split("-");
        definirURL("http://localhost/Room-control/eliminarUsuario.php");
        urlParaVisitar.append("?ids=");

        for(String idUs: ids)
            urlParaVisitar.append(idUs).append("-");
        
        urlParaVisitar.deleteCharAt(urlParaVisitar.length()-1);
        
        enviarDatos();
    }
    
    public void iniciarSesion(String idUsuario, String contra){
        definirURL("http://localhost/Room-control/iniciarSesion.php");
        try{
            urlParaVisitar.append("?");
            urlParaVisitar.append("idUs=").append(idUsuario).append("&");
            urlParaVisitar.append("contra=").append(URLEncoder.encode(contra, "utf-8"));
        }catch(UnsupportedEncodingException uee){
            new Mensaje().mostrarMensaje(Mensaje.ERROR, uee.toString());
        }
        
        enviarDatos();
    }
    
    public String recibirListaUsuarios(){
        definirURL("http://localhost/Room-control/obtenerUsuarios.php");
        return peticionDeRespuesta();
    }
    
    public String recibirEstadoActual(){
        definirURL("http://localhost/Room-control/recibirEstadoActual.php");
        return peticionDeRespuesta();
    }
    
    public String recibirHistorial(){
        definirURL("http://localhost/Room-control/historial.php");
        return peticionDeRespuesta();
    }
    
    public void enviarLuz(String porcentaje){
        definirURL("http://localhost/Room-control/enviarLuz.php");
        urlParaVisitar.append("?luz=").append(porcentaje);
        
        enviarDatos();
    }
    
    public void enviarSenalVentilador(String senal){
        definirURL("http://localhost/Room-control/enviarSenalVentilador.php");
        urlParaVisitar.append("?vent=").append(senal);
        
        enviarDatos();
    }
    
    
    
    private void enviarDatos(){
        try{
            // Crear un objeto de tipo URL
            URL url= new URL(urlParaVisitar.toString());
            
            // Abrir la conexión e indicar que será de tipo GET
            HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod(metodo);
        }catch(IOException ex){
            new Mensaje().mostrarMensaje(Mensaje.ERROR, ex.toString());
        }
    }
    
    private void definirURL(String url){
        urlParaVisitar= new StringBuilder(url);
    }
    
    private String obtenerURL(){
        return urlParaVisitar.toString();
    }
}
