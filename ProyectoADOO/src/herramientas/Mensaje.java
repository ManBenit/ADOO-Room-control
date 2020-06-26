package herramientas;

import javax.swing.JOptionPane;

public class Mensaje{
    public static final int INFO= 1;
    public static final int WARNING= 2;
    public static final int ERROR= 0;
    public static final int QUESTION= 3;
    
    public void mostrarMensaje(int tipo, String mensaje){
        JOptionPane.showMessageDialog(null, mensaje, "Room-control dice:", tipo);
    }
    
    public void mostrarMensaje(int tipo, String mensaje, String deQuien){
        JOptionPane.showMessageDialog(null, mensaje, deQuien, tipo);
    }
    
    public String entrarInfo(int tipo, String mensaje, String titulo){
        return JOptionPane.showInputDialog(null, mensaje, titulo, tipo);
    }
    
    public String entrarInfo(String mensaje, Object[] opciones){
        Object ret= JOptionPane.showInputDialog(null, mensaje, "Elegir", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        return ret.toString();
    }
    
    public int seleccionarSiNo(String mensaje){
        int s=0;
        int seleccion = JOptionPane.showOptionDialog( null, mensaje,
            "Room-control dice:",JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
            new Object[] {"No", "Sí"},"Sí");

        if (seleccion != -1)
           s= seleccion;
        
        return s;
    }
    
}
