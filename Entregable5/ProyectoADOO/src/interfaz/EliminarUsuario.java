package interfaz;

import herramientas.Mensaje;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import proyectoadoo.ServicioWeb;

public class EliminarUsuario extends JDialog {
    private ServicioWeb ws;
    private ArrayList<JCheckBox> checkBoxList;
    private ArrayList<String> idg;
    private String nombre;
    
    public EliminarUsuario(JFrame parent, boolean modal, ServicioWeb ws, String nombre) {
        super(parent, modal);
        checkBoxList= new ArrayList<>();
        idg= new ArrayList<>();
        this.ws= ws;
        this.nombre= nombre;
        initComponents();
        ImageIcon Imagen= new ImageIcon(getClass().getResource("../Imagenes/fondo.jpg"));  
        ImageIcon ImgScal=new ImageIcon(Imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
        lblFondo.setIcon(ImgScal);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Imagenes/icono-pequeño.png")));
        setTitle("Eliminación de usuarios.");
        setResizable(true);
        setLocationRelativeTo(null);
        Cadena_id_nom(ws.recibirListaUsuarios());
        colocarCheckBox();
    }
    
    private void colocarCheckBox(){
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        
        for(JCheckBox check: checkBoxList) 
            p.add(check);
    }
    
    private void Cadena_id_nom(String db){
        String cad[]= db.split("@");
        
        for(String id_nom: cad){
            String[] partes= id_nom.split("-");
            
            JCheckBox check= new JCheckBox(partes[1]); // |_| Nombre
            if(partes[1].equals(nombre))
                check.setName("Yo"); //El usuario es quien inició sesión
            else
                check.setName(partes[0]); //El nombre del checkbox es el id
            check.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(check.isSelected())
                        idg.add(check.getName());
                    else{
                        for(String buscador: idg){
                            if(check.getName().equals(buscador)){
                                idg.remove(buscador);
                                break;
                            }
                        }
                    }
                } 
            });
            
            if(!check.getName().equals("Yo"))
                checkBoxList.add(check);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p = new javax.swing.JPanel();
        btneliminar = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        p.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Eliminar Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 2, 18))); // NOI18N

        javax.swing.GroupLayout pLayout = new javax.swing.GroupLayout(p);
        p.setLayout(pLayout);
        pLayout.setHorizontalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        pLayout.setVerticalGroup(
            pLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        btneliminar.setBackground(new java.awt.Color(0, 0, 255));
        btneliminar.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(255, 255, 255));
        btneliminar.setText("Eliminar");
        btneliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btneliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btneliminarMouseExited(evt);
            }
        });
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(434, 434, 434)
                            .addComponent(btneliminar)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(204, 204, 204)
                    .addComponent(btneliminar)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminarMouseEntered
        btneliminar.setBackground(new Color(51,204,255));
    }//GEN-LAST:event_btneliminarMouseEntered

    private void btneliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneliminarMouseExited
        btneliminar.setBackground(Color.blue);
        btneliminar.setForeground(Color.white);
    }//GEN-LAST:event_btneliminarMouseExited

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        try{
            StringBuffer ids= new StringBuffer("");
            for(String id: idg)
            ids.append(id).append("-");

            ids.deleteCharAt(ids.length()-1);

            ws.eliminarUsuario(ids.toString());
            if(Integer.parseInt(ws.peticionDeRespuesta())==1){
                new Mensaje().mostrarMensaje(Mensaje.INFO, "Usuario(s) eliminado(s) con éxito.", "Room-control");
                this.dispose();
            }
            else
                new Mensaje().mostrarMensaje(Mensaje.WARNING, "Error al eliminar.", "Room-control");
        }catch(StringIndexOutOfBoundsException ex){
            new Mensaje().mostrarMensaje(Mensaje.WARNING, "Seleccione a alguien", "Room-control");
        }
    }//GEN-LAST:event_btneliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JPanel p;
    // End of variables declaration//GEN-END:variables
}
