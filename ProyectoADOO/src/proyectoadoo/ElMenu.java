package proyectoadoo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import herramientas.Mensaje;

public class ElMenu extends JFrame implements ActionListener{

    public ElMenu() {
        initComponents();
        escuchadores();
        
        //JDialog
        jDialog1.setLocationRelativeTo(null);
        jDialog1.setSize(new Dimension(750, 400));
        jDialog1.setTitle("Lista de escuelas");
        
        llenarHistorial();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btn = new javax.swing.JButton();
        menuEnvio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnHist = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn.setText("jButton1");

        menuEnvio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int luz", "Vent" }));

        jLabel1.setText("Envío");

        btnHist.setText("Historial");

        jButton1.setText("Regisytrar nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("eliminar usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(menuEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnHist)
                        .addGap(0, 177, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menuEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHist))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(0, 168, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        if(admin)
//            setVisible(ventanaAdmin)
//        else
//            mostrar ventana de mortales :v
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    public void actionPerformed(ActionEvent evt){
        Object pressed= evt.getSource();
        
        if(pressed==btnHist){
            jDialog1.setVisible(true);
        }
        else if(pressed==menuEnvio){
            //int opc= menuEnvio.getSelectedIndex();
            String opc= menuEnvio.getSelectedItem().toString();
            //if(opc==0){
            if(opc.equals("int luz")){
                Object[] o= {"10","20", "...", "100"};
                Mensaje mje= new Mensaje();
                String s= mje.entrarInfo("Selecciona temperatura", o);
                System.out.println(s);
            }
            else{
                System.out.println("Enviar encendido/apagado");
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JButton btnHist;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> menuEnvio;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    private void escuchadores(){
        btnHist.addActionListener(this);
        menuEnvio.addActionListener(this);
    }
    
    private void llenarHistorial(){
        DefaultTableModel dtm;
        
        String[] nombreCol= {"Fecha", "Hora", "Temperatura", "Humedad", "etcetcetc"};
        
        dtm= new DefaultTableModel(null, nombreCol);
        
        Object[] o= {"cosa", "cosa"};
        
        dtm.addRow(o);
        dtm.addRow(o);
        dtm.addRow(o);
        dtm.addRow(o);
        dtm.addRow(o);
        dtm.addRow(o);
        dtm.addRow(o);
        dtm.addRow(o);
        
        tabla.setModel(dtm);
    }
    /*
    usuario----->pedir historial--->consultar base---->devolver tuplas
    consulta a entilador avtivado por humo
    fecha1, hora 1, temperatura, humedad, sensor por humo
    fecha2, hora 2, temperatura2, humedad, sensor por humo
    fecha1, hora 1, temperatura, humedad, sensor por humo
    fecha1, hora 1, temperatura, humedad, sensor por humo
    */
}
