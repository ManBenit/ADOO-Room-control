package herramientas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServicioWeb {
    private String metodo;
    private StringBuilder urlParaVisitar;
    
    public ServicioWeb(String url, String metodo){
        urlParaVisitar= new StringBuilder(url);
        this.metodo= metodo;
    }
    
    private String obtenerURL(){
        return urlParaVisitar.toString();
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
    
    public void nuevoUsuario(String nombre, String apaterno, String amaterno, String contra, boolean admin){
        int ad=0;
        if(admin) ad=1;
        String fecha= new Fecha("yyyy-MM-dd").getSFecha();
        String idUsuario= new GeneradorDeUsuario(nombre, apaterno, amaterno, fecha).obtenerUsuario();
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
        try{
            // Crear un objeto de tipo URL
            URL url= new URL(urlParaVisitar.toString());

            // Abrir la conexión e indicar que será de tipo GET
            HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod(metodo);
            
            
            
//            // Búferes para leer
//            BufferedReader rd= new BufferedReader(new InputStreamReader(conexion.getInputStream()));
//            String linea;
//            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
//            while ((linea= rd.readLine()) != null) {
//                resultado.append(linea);
//            }
//
//            rd.close();
        }catch(IOException ex){
            new Mensaje().mostrarMensaje(Mensaje.ERROR, ex.toString());
        }
    }
}
