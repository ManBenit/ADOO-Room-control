package herramientas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fecha {
    private SimpleDateFormat sdf;
    private Calendar fecha;
    
    //El constructor recibe un formato que acepta el constructor de la clase SimpleDateFormat
    public Fecha(String formato){
        fecha = Calendar.getInstance();
        sdf= new SimpleDateFormat(formato);
    }
    
    //Si no se ingresa el formato, se llama al contructor sin parámetros de la clase SimpleDateFormat
    public Fecha(){
        fecha = Calendar.getInstance();
        sdf= new SimpleDateFormat();
    }
    
    //Los siguientes 3 métodos devuelven una fecha que se especifique
    public Date getDFecha(int dia, int mes, int anio){
        fecha.set(anio, mes, dia);
        return fecha.getTime();
    }
    
    public String getSFecha(int dia, int mes, int anio){
        fecha.set(anio, mes, dia);
        return sdf.format(fecha.getTime());
    }
    
    public Calendar getCFecha(int dia, int mes, int anio){
        fecha.set(anio, mes, dia);
        return fecha;
    }
    
    //Los siguientes 3 métodos devuelven la fecha actual
    public Date getDFecha(){
        return fecha.getTime();
    }
    
    public String getSFecha(){
        return sdf.format(fecha.getTime());
    }
    
    public Calendar getCFecha(){
        return fecha;
    }
    
    public String getHora(){ //Beta, falla cuando segundos es menor a 10
        StringBuilder hora= new StringBuilder("");
        StringBuilder minuto= new StringBuilder("");
        StringBuilder segundo= new StringBuilder("");
        
        if(getDFecha().getHours()<10) hora.append(0).append(getDFecha().getHours());
        else hora.append(getDFecha().getHours());
        
        if(getDFecha().getMinutes()<10) hora.append(0).append(getDFecha().getMinutes());
        else minuto.append(getDFecha().getMinutes());
        
        if(getDFecha().getSeconds()<10) hora.append(0).append(getDFecha().getSeconds());
        else segundo.append(getDFecha().getSeconds());
            
        return hora.toString()+":"+minuto.toString()+":"+segundo.toString();
    }
}