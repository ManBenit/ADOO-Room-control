package interfaz;

import herramientas.Fecha;
import herramientas.GeneradorDeUsuario;
import herramientas.Mensaje;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JDialog;
import proyectoadoo.ServicioWeb;

public class NuevoUsuario extends JDialog {
    private ServicioWeb ws;
    public NuevoUsuario(JFrame parent, boolean modal, ServicioWeb ws) {
        super(parent, modal);
        this.ws= ws;
        initComponents();
        ImageIcon Imagen= new ImageIcon(getClass().getResource("../Imagenes/fondo.jpg"));  
        ImageIcon ImgScal=new ImageIcon(Imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
        lblFondo.setIcon(ImgScal);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Imagenes/icono-pequeño.png")));
        setTitle("Registro de nuevos usuarios.");
        setResizable(true);
        setLocationRelativeTo(null);
    }
    
    private void limpiarCajas(){
            jtfNombres.setText(null);
            jtfApaterno.setText(null);
            jtfAmaterno.setText(null);
            jtfContra.setText(null);
            checkAdmin.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkAdmin = new javax.swing.JCheckBox();
        btnRegistrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNombres = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfApaterno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfAmaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfContra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkAdmin.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        checkAdmin.setText("Administrador");
        getContentPane().add(checkAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 386, -1, -1));

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 255));
        btnRegistrar.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
        });
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 386, -1, -1));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 2, 18)); // NOI18N
        jLabel1.setText("Nombre(s)");

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI", 2, 18)); // NOI18N
        jLabel5.setText("Apellido Paterno");

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei UI", 2, 18)); // NOI18N
        jLabel7.setText("Apellido Materno");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI", 2, 18)); // NOI18N
        jLabel6.setText("Contraseña");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jtfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jtfApaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jtfAmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(jtfContra, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 21, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 25, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jtfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(42, 42, 42)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jtfApaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(42, 42, 42)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jtfAmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(42, 42, 42)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtfContra, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 26, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 600, 290));

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 24)); // NOI18N
        jLabel2.setText("Registro Nuevo Usuario");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 290, 40));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String nom= jtfNombres.getText().toUpperCase();
        String apat= jtfApaterno.getText().toUpperCase();
        String amat= jtfAmaterno.getText().toUpperCase();
        String contra= jtfContra.getText();
        if(nom.equals("") || apat.equals("") || amat.equals("") || contra.equals(""))
            new Mensaje().mostrarMensaje(Mensaje.QUESTION, "Datos incompletos", "Room-control");
        else{
            String fecha= new Fecha("yyyy-MM-dd").getSFecha();
            String idUsuario= new GeneradorDeUsuario(nom, apat, amat, fecha).obtenerUsuario();

            ws.nuevoUsuario(
                idUsuario,
                nom,
                apat,
                amat,
                fecha,
                contra,
                checkAdmin.isSelected()
            );

            String respuesta= ws.peticionDeRespuesta();
            if(respuesta.equals("1")){
                new Mensaje().mostrarMensaje(Mensaje.INFO, "Usuario registrado con éxito.\nID: "+idUsuario, "Room-control");
                limpiarCajas();
            }
            else
                new Mensaje().mostrarMensaje(Mensaje.WARNING, respuesta, "Room-control");
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseEntered
        btnRegistrar.setBackground(new Color(51,204,255));
    }//GEN-LAST:event_btnRegistrarMouseEntered

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        btnRegistrar.setBackground(Color.blue);
        btnRegistrar.setForeground(Color.white);
    }//GEN-LAST:event_btnRegistrarMouseExited

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox checkAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jtfAmaterno;
    private javax.swing.JTextField jtfApaterno;
    private javax.swing.JTextField jtfContra;
    private javax.swing.JTextField jtfNombres;
    private javax.swing.JLabel lblFondo;
    // End of variables declaration//GEN-END:variables
}
