package interfaz;

import javax.swing.JFrame;
import proyectoadoo.ServicioWeb;
import herramientas.Mensaje;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Inicio extends JFrame {
    private ServicioWeb ws;
    public Inicio(ServicioWeb ws) {
        this.ws= ws;
        initComponents();
        ImageIcon Imagen= new ImageIcon(getClass().getResource("../Imagenes/fondo.jpg"));  
        ImageIcon ImgScal=new ImageIcon(Imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
        lblFondo.setIcon(ImgScal);
        ImageIcon Imagen2= new ImageIcon(getClass().getResource("../Imagenes/roomcontroltransparente.png"));  
        ImageIcon ImgScal2=new ImageIcon(Imagen2.getImage().getScaledInstance(lblRC.getWidth(),lblRC.getHeight(),Image.SCALE_DEFAULT));
        lblRC.setIcon(ImgScal2);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Imagenes/icono-pequeño.png")));
        setTitle("Room-control. Inicio de sesión.");
        setResizable(true);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jpfContra = new javax.swing.JPasswordField();
        jtfUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        lblRC = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel2.setText("Contraseña");

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel1.setText("Usuario");

        btnIngresar.setBackground(new java.awt.Color(0, 0, 240));
        btnIngresar.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarMouseExited(evt);
            }
        });
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpfContra, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnIngresar)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpfContra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnIngresar)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 550, 140));
        getContentPane().add(lblRC, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 80));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void limpiarCajas(){
        jtfUsuario.setText(null);
        jpfContra.setText (null);
    }
    
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        if(jtfUsuario.getText().equals("") || jpfContra.getText().equals(""))
            new Mensaje().mostrarMensaje(Mensaje.QUESTION, "Datos incompletos", "Room-control");
        else{
            ws.iniciarSesion(jtfUsuario.getText(), jpfContra.getText());
            String[] respuesta= ws.peticionDeRespuesta().split("@");

            if(respuesta[0].equals("1")){
                new Mensaje().mostrarMensaje(Mensaje.INFO, "Bienvenido\nIniciaste sesión como Administrador.", "Room-control");
                new MenuAdmin(ws, respuesta[1]).setVisible(true);
                this.dispose();
            }else if(respuesta[0].equals("0")){
                new Mensaje().mostrarMensaje(Mensaje.INFO, "Bienvenido.\nIniciaste sesion.", "Room-control");
                new MenuNoAdmin(ws, respuesta[1]).setVisible(true);
                this.dispose();
            }else{
                new Mensaje().mostrarMensaje(Mensaje.WARNING, respuesta[0], "Room-control");
                limpiarCajas();
            }
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseEntered
        btnIngresar.setBackground(new Color(51,204,255));
    }//GEN-LAST:event_btnIngresarMouseEntered

    private void btnIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseExited
        btnIngresar.setBackground(Color.blue);
        btnIngresar.setForeground(Color.white);
    }//GEN-LAST:event_btnIngresarMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jpfContra;
    private javax.swing.JTextField jtfUsuario;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblRC;
    // End of variables declaration//GEN-END:variables

   
}
