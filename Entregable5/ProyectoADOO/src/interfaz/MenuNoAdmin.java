package interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import herramientas.Mensaje;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import proyectoadoo.ServicioWeb;

public class MenuNoAdmin extends JFrame{
    private ServicioWeb ws;
    private String nombre;
    private String valores_txtarea[]=new String[4];
    private ArrayList<JLabel> listaPHS; //Presencia, humo y sonido
    
    public MenuNoAdmin(ServicioWeb ws, String nombre) {
        this.ws= ws;
        this.nombre= nombre;
        listaPHS= new ArrayList<>();
        initComponents();
        ImageIcon Imagen= new ImageIcon(getClass().getResource("../Imagenes/fondo.jpg"));  
        ImageIcon ImgScal=new ImageIcon(Imagen.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
        lblFondo.setIcon(ImgScal);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("../Imagenes/icono-pequeño.png")));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle(this.nombre);
        setResizable(true);
        setLocationRelativeTo(null);
        menuEnvio.setEnabled(false);
        txtAreaVent.setEditable(false);
        listaPHS.add(LabelPres);
        listaPHS.add(LabelHumo);
        listaPHS.add(LabelSonido);
        
        //llenarHistorial();
        
        //Consulta infinita del estado actual
        Thread hilo= new Thread(new Runnable(){
            @Override
            public void run(){
                while(true)
                    Cadena_valores(ws.recibirEstadoActual());
            }
        });
        hilo.start();
    }
    
    private void Cadena_valores(String cad){
//        cad="23@15@100@1@0@0@0*0*0*1";
        txtAreaVent.setText(null);
        String cad_separada[]=cad.split("@");
        
        //Temperatura: índice 0        
        double farenheit= 1.8*Integer.parseInt(cad_separada[0])+32;
        String gradosf= String.format("%.2f", farenheit);
        labelTempC.setText("");
        LabelTemp.setText(gradosf+"°F");
        labelTempC.setText(cad_separada[0]+"°C");
        
        //Humedad: índice 1
        LabelHumedad.setText(cad_separada[1]+"%");
        
        //Luz: índice 2
        LabelLuz.setText(cad_separada[2]+"%");
        
        //Presencia, Humo, Sonido: índices 3-5
        for (int i = 3; i < cad_separada.length-1; i++) {
            if(cad_separada[i].equals("0")){
                listaPHS.get(i-3).setText("no detectado");
                listaPHS.get(i-3).setForeground(Color.black);
            }else{
                listaPHS.get(i-3).setText("detectado");
                listaPHS.get(i-3).setForeground(Color.red);
            }
        }
      
        //Ventilador: índice 6
        String cad_venti[]=cad_separada[6].split("\\*");
        for (int i = 0; i < cad_venti.length; i++)
            valores_txtarea[i]=cad_venti[i];
        
        for (int i = 0; i < cad_venti.length; i++) {
            if(cad_venti[i].equals("1")){
                cad_venti[i]="encendido";
            }else{
                if(cad_venti[i].equals("-"))
                    cad_venti[i]="-";
                else
                    cad_venti[i]="apagado";
            }
        }
        
        for(String estado: cad_venti){
            if(estado.equals("encendido")){
                txtAreaVent.setForeground(Color.red);
                break;
            }
            else
                txtAreaVent.setForeground(Color.black);
        }
        
        txtAreaVent.append("Temperatura= "+cad_venti[0]+"\nHumedad= "+cad_venti[1]+"\nHumo= "+cad_venti[2]+"\nUsuario= "+cad_venti[3]);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        labelTempC = new javax.swing.JLabel();
        LabelTemp = new javax.swing.JLabel();
        LabelPres = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LabelHumedad = new javax.swing.JLabel();
        LabelSonido = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LabelHumo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LabelLuz = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        CheckBoxControlu = new javax.swing.JCheckBox();
        menuEnvio = new javax.swing.JComboBox<>();
        btnHist = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaVent = new javax.swing.JTextArea();
        btnCerrarSesion = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sensores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 2, 18))); // NOI18N
        jPanel1.setOpaque(false);

        labelTempC.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        labelTempC.setText("jLabel9");

        LabelTemp.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        LabelTemp.setText("jLabel9");

        LabelPres.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        LabelPres.setText("jLabel12");

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel3.setText("Humedad");

        LabelHumedad.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        LabelHumedad.setText("jLabel10");

        LabelSonido.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        LabelSonido.setText("jLabel14");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel6.setText("Humo");

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel4.setText("Luz");

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel7.setText("Sonido");

        LabelHumo.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        LabelHumo.setText("jLabel13");

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel5.setText("Presencia");

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        jLabel2.setText("Temperatura");

        LabelLuz.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        LabelLuz.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(65, 65, 65)
                        .addComponent(LabelSonido))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(65, 65, 65)
                        .addComponent(LabelPres))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(65, 65, 65)
                        .addComponent(LabelHumo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelTemp)
                            .addComponent(labelTempC))))
                .addGap(105, 105, 105)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelLuz)
                    .addComponent(LabelHumedad))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LabelTemp))
                .addGap(26, 26, 26)
                .addComponent(labelTempC)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPres)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelHumo)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelSonido)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelHumedad)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelLuz)
                            .addComponent(jLabel4))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 690, 290));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 2, 18))); // NOI18N
        jPanel2.setOpaque(false);

        CheckBoxControlu.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        CheckBoxControlu.setText("Control de usuario");
        CheckBoxControlu.setOpaque(false);
        CheckBoxControlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxControluActionPerformed(evt);
            }
        });

        menuEnvio.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        menuEnvio.setForeground(new java.awt.Color(255, 255, 255));
        menuEnvio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enviar luz", "Ventilador" }));
        menuEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEnvioActionPerformed(evt);
            }
        });

        btnHist.setBackground(new java.awt.Color(0, 0, 255));
        btnHist.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 18)); // NOI18N
        btnHist.setForeground(new java.awt.Color(255, 255, 255));
        btnHist.setText("Historial");
        btnHist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistMouseExited(evt);
            }
        });
        btnHist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(btnHist)
                .addGap(62, 62, 62)
                .addComponent(CheckBoxControlu)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CheckBoxControlu)
                        .addComponent(btnHist)))
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 620, 100));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventilador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 2, 18))); // NOI18N
        jPanel3.setOpaque(false);

        txtAreaVent.setColumns(20);
        txtAreaVent.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 14)); // NOI18N
        txtAreaVent.setRows(5);
        txtAreaVent.setOpaque(false);
        jScrollPane1.setViewportView(txtAreaVent);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 300, 140));

        btnCerrarSesion.setBackground(new java.awt.Color(0, 0, 255));
        btnCerrarSesion.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar sesion");
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, -1, -1));
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1080, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckBoxControluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxControluActionPerformed
        if(!CheckBoxControlu.isSelected()){
            menuEnvio.setEnabled(false);
            ws.enviarSenalVentilador("-");
            String vent= ws.peticionDeRespuesta();
            ws.enviarLuz("-");
            String luz= ws.peticionDeRespuesta();
            if(vent.equals("1") && luz.equals("1"))
                new Mensaje().mostrarMensaje(Mensaje.INFO, "CORRECTO\nControl cedido al circuito.", "Room-control");
            else
               new Mensaje().mostrarMensaje(Mensaje.ERROR, "Error inesperado", "Room-control");
        }
        else
            menuEnvio.setEnabled(true);
    }//GEN-LAST:event_CheckBoxControluActionPerformed

    private void menuEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEnvioActionPerformed
        //int opc= menuEnvio.getSelectedIndex();
        String opc= menuEnvio.getSelectedItem().toString();
        //if(opc==0){
            if(opc.equals("Enviar luz")){
                Object[] o= {"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
                String sel= new Mensaje().entrarInfo("Selecciona temperatura", o);
                ws.enviarLuz(sel);
                if(ws.peticionDeRespuesta().equals("1"))
                new Mensaje().mostrarMensaje(Mensaje.INFO, "Luz enviada con éxito", "Room-control");
                else
                new Mensaje().mostrarMensaje(Mensaje.ERROR, "Error inesperado", "Room-control");
            }
            else{
                //System.out.println("Enviar encendido/apagado");
                Object[] o= {"Encender","Apagar"};
                String s= new Mensaje().entrarInfo("Estado del ventilador", o);

                if(s.equals("Encender")){
                    boolean bndVent=false; //Si se va a encender, es porque está pagado
                    for (int i = 0; i < valores_txtarea.length; i++) {
                        if(valores_txtarea[i].equals("1")){
                            bndVent=true;
                            break;
                        }
                    }

                    if(!bndVent){
                        ws.enviarSenalVentilador("1");
                        if(ws.peticionDeRespuesta().equals("1"))
                        new Mensaje().mostrarMensaje(Mensaje.INFO, "CORRECTO\nEl ventilador se encenderá pronto", "Room-control");
                        else
                        new Mensaje().mostrarMensaje(Mensaje.ERROR, "Error inesperado", "Room-control");
                    }
                }else{
                    boolean bndVent=true; //Si se va a apagar, es porque está prendido
                    for (int i = 0; i < valores_txtarea.length; i++) {
                        if(valores_txtarea[i].equals("1")){
                            bndVent=true;
                            break;
                        }
                    }

                    if(bndVent){
                        ws.enviarSenalVentilador("0");
                        if(ws.peticionDeRespuesta().equals("1"))
                        new Mensaje().mostrarMensaje(Mensaje.INFO, "CORRECTO\nEl ventilador se apagará pronto", "Room-control");
                        else
                        new Mensaje().mostrarMensaje(Mensaje.ERROR, "Error inesperado", "Room-control");
                    }
                }

            }
    }//GEN-LAST:event_menuEnvioActionPerformed

    private void btnHistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistMouseEntered
        btnHist.setBackground(new Color(51,204,255));
    }//GEN-LAST:event_btnHistMouseEntered

    private void btnHistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistMouseExited
        btnHist.setBackground(Color.blue);
        btnHist.setForeground(Color.white);
    }//GEN-LAST:event_btnHistMouseExited

    private void btnHistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistActionPerformed
        new Historial(this, true, ws, nombre).setVisible(true);
    }//GEN-LAST:event_btnHistActionPerformed

    private void btnCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseEntered
        btnCerrarSesion.setBackground(new Color(51,204,255));
    }//GEN-LAST:event_btnCerrarSesionMouseEntered

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited
        btnCerrarSesion.setBackground(Color.blue);
        btnCerrarSesion.setForeground(Color.white);
    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        new Inicio(ws).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckBoxControlu;
    private javax.swing.JLabel LabelHumedad;
    private javax.swing.JLabel LabelHumo;
    private javax.swing.JLabel LabelLuz;
    private javax.swing.JLabel LabelPres;
    private javax.swing.JLabel LabelSonido;
    private javax.swing.JLabel LabelTemp;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnHist;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTempC;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JComboBox<String> menuEnvio;
    private javax.swing.JTextArea txtAreaVent;
    // End of variables declaration//GEN-END:variables

    
}
