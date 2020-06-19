package herramientas;

import java.util.ArrayList;
import java.util.Random;

//Generador de nombres de usuario para Room-control
//Usuario de 8 caracteres alfanuméricos

//- validador (3): (año+mes+dia)%(mes*dia)
//- identificador (3): nombre, ap. paterno, ap. materno
//      si al explotar los campos por el espacio se consigue un arreglo longitud 1, se usan las iniciales.
//      si la explosión resulta con arreglo longitud mayor a 1 en algún campo, se realiza la suma ascii de 
//          los elementos del arreglo obteniendo una sola letra que será concatenada con las iniciales obtenidas
//          o generadas de los otros 2 campos.
//- numero extra (2): Con base en la semilla aleatoria (nombre, ap paterno o ap materno) para obtener su último caracter.
//- caracter extra (2): Numero basado en el entero proveniente de la semilla mapeado entre 0 y 9.
public class GeneradorDeUsuario {
    private StringBuilder usuario;
    private String validador, identificador;
    private String compVal; //complementador del validador
    
    public GeneradorDeUsuario(String nombre, String apaterno, String amaterno, String fecha){
        usuario= new StringBuilder("");
        compVal= String.valueOf(new Random().nextInt(10)); //de 0 a 9
        
        hacerValidador(fecha);
        hacerIdentificador(nombre, apaterno, amaterno);
        
        int opcSemilla= new Random().nextInt(3);
        if(opcSemilla==0)
            generarUsuario(nombre);
        else if(opcSemilla==1)
            generarUsuario(apaterno);
        else if(opcSemilla==2)
            generarUsuario(amaterno);
    }
    
    public String obtenerUsuario(){
        return usuario.toString();
    }
    
    private void hacerValidador(String fecha){
        int[] fechaP= new int[3];
        int i=0;
        String[] fP= fecha.split("-"); 
        for(String f: fP){
            fechaP[i]= Integer.parseInt(f);
            i+=1;
        }
        
        validador= String.valueOf((fechaP[0]+fechaP[1]+fechaP[2])%(fechaP[1]*fechaP[2]));
        
        if(validador.length()<3){
            StringBuilder sb= new StringBuilder(validador);
            
            for(int j=validador.length(); j<3; j++)
                sb.append(compVal);
            validador= sb.toString();
        }
    }
    
    private void hacerIdentificador(String nombre, String apaterno, String amaterno){
        StringBuilder sb= new StringBuilder("");
        String[] nombreP= nombre.split(" ");
        String[] apaternoP= apaterno.split(" ");
        String[] amaternoP= amaterno.split(" ");
        
        //Iniciales del nombre
        if(nombreP.length>1)
            sb.append(obtenerAsciiDeIniciales(nombreP));
        else
            sb.append(nombreP[0].toCharArray()[0]);
        
        //Iniciales del apellido paterno
        if(apaternoP.length>1)
            sb.append(obtenerAsciiDeIniciales(apaternoP));
        else
            sb.append(apaternoP[0].toCharArray()[0]);
        
        //Iniciales del apellido materno
        if(amaternoP.length>1)
            sb.append(obtenerAsciiDeIniciales(amaternoP));
        else
            sb.append(amaternoP[0].toCharArray()[0]);
        
        identificador= sb.toString();
    }
    
    private char obtenerAsciiDeIniciales(String[] cadenaEnPartes){
        int suma=0;
        ArrayList<Integer> iniciales= new ArrayList<>();
        
        //para cada elemento de la cadena en partes:
        // se parte completamente la cadena y se obtiene la inicial (índice 0)
        // esa inicial para a convertirse en caracter y finalmente a entero
        // el entero se mete a la lista
        for (String cadPart: cadenaEnPartes) 
            iniciales.add(Integer.valueOf(cadPart.split("")[0].charAt(0)));
        
        for(Integer i: iniciales)
            suma+= i;
        
        return (char)( 65+(suma%25) ); //Devolver un caracter entre A-Z
    }
    
    private void generarUsuario(String semilla){
        int ultimoCaracter= (int)semilla.toCharArray()[semilla.length()-1];
        
        //Elementos de relleno: 
        // caracter basado en una semilla proveniente del nombre
        //      aleatoriamente es nombre, ap. paterno o ap. materno.
        // numero basado en el entero proveniente de la semilla mapeado entre 0 y 9.
        char caracterExtra= (char)( 65+(ultimoCaracter%25) );
        int numeroExtra= ultimoCaracter%(new Random().nextInt(8)+1);
        
        //Se organiza el usuario en 4 modos diferentes
        switch (new Random().nextInt(8)){
            case 0:
                usuario.append(validador).append(caracterExtra).append(identificador).append(numeroExtra);
                break;
            case 1:
                usuario.append(validador).append(numeroExtra).append(identificador).append(caracterExtra);
                break;
            case 2:
                usuario.append(identificador).append(caracterExtra).append(validador).append(numeroExtra);
                break;
            case 3:
                usuario.append(identificador).append(numeroExtra).append(validador).append(caracterExtra);
                break;
            case 4:
                usuario.append(caracterExtra).append(validador).append(numeroExtra).append(identificador);
                break;
            case 5:
                usuario.append(caracterExtra).append(identificador).append(numeroExtra).append(validador);
                break;
            case 6:
                usuario.append(numeroExtra).append(validador).append(caracterExtra).append(identificador);
                break;
            case 7:
                usuario.append(numeroExtra).append(identificador).append(caracterExtra).append(validador);
                break;
        }
        
        //Modos de organización para el usuario:
        /*
        v c i n
        v n i c
        i c v n
        i n v c
        c v n i
        c i n v
        n v c i
        n i c v
        */
    }
}
