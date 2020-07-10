package interfaz;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import proyectoadoo.ServicioWeb;

public class Historial extends JDialog {
    private ServicioWeb ws;
    
    //nomPestañas debe relacionarse con lo asignado a $tablas de historial.php línea 6
    private final String[] nomPestañas= {
        "Temperatura/Humedad", "Luz", "Presencia", "Humo", "Sonido", "Ventilador"
    };
    
    public Historial(JFrame parent, boolean modal, ServicioWeb ws, String nombre) {
        super(parent, modal);
        this.ws= ws;
        initComponents();
        ImageIcon Imagen= new ImageIcon(getClass().getResource("../Imagenes/fondo.jpg"));  
        ImageIcon ImgScal=new ImageIcon(Imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
        lblFondo.setIcon(ImgScal);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Imagenes/icono-pequeño.png")));
        setTitle("Historial.");
        setResizable(true);
        setLocationRelativeTo(null);
        
        lbNombre.setText(nombre);
        cargarContenido();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pestañas = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pestañas.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel1.setText("Sesión:");

        lbNombre.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pestañas)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 331, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(pestañas, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JTabbedPane pestañas;
    // End of variables declaration//GEN-END:variables

    private void cargarContenido(){
        ArrayList<String[]> columna= new ArrayList<>();
        ArrayList<ArrayList<String[]>> contenido= new ArrayList<>();
        ArrayList<JScrollPane> contenidoPes= new ArrayList<>();
        String resultado= ws.recibirHistorial();
        
        String[] informacion= resultado.split("@");
        for(String datos: informacion){
            String[] partes= datos.split("\\*");
            
            String[] columnas= partes[0].split("#");
            String[] cols= new String[columnas.length];
            for(int i=0; i<columnas.length; i++)
                cols[i]= columnas[i];
            columna.add(cols);
            
            ArrayList<String[]> tupla= new ArrayList<>();
            String[] fila= partes[1].split("/");
            for(int i=0; i<fila.length; i++){
                String[] valor= fila[i].split("#");
                String[] valTupla= new String[valor.length];
                for(int o=0; o<valor.length; o++)
                    valTupla[o]= valor[o];
                tupla.add(valTupla);
            }
            
            contenido.add(tupla);
        }
        
        for(int i=0; i<columna.size(); i++){
            JTable tabla= new JTable(crearContenidoTabla(columna.get(i), contenido.get(i)));
            tabla.setEnabled(false);
            JScrollPane jsp= new JScrollPane(tabla);
            contenidoPes.add(jsp);
        }
        
        for(int i=0; i<nomPestañas.length; i++)
            pestañas.addTab(nomPestañas[i], contenidoPes.get(i));
    }
    
    private DefaultTableModel crearContenidoTabla(String[] nomCols, ArrayList<String[]> filas){
        DefaultTableModel dtm= new DefaultTableModel(null, nomCols);
        for(String[] tupla: filas)
            dtm.addRow(tupla);
        
        return dtm;
    }
    
}
