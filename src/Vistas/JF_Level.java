/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlador.CategoriaControlador;
import Controlador.HistoricoControlador;
import Controlador.OpcionControlador;
import Controlador.PreguntaControlador;
import Controlador.RondaControlador;
import VO.Categoria;
import VO.Historico;
import VO.Opcion;
import VO.Pregunta;
import VO.Ronda;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josep
 */
public class JF_Level extends javax.swing.JFrame {
    int ronda_maxima = 5; //Establecer la ronda máxima del juego (por ahora solo 5 rondas)
    ArrayList<Ronda> rondaList;
    ArrayList<Opcion> listaOpciones;
    Historico his = new Historico();
    Ronda ronActual = new Ronda(); //objeto para llamar método consultar datos del usuario ingresado en la base de datos

    /**
     * Creates new form JF_LOGGIN
  
     */
    BackgroundPanel background = new BackgroundPanel();
    public JF_Level() {
        this.setContentPane(background);
        initComponents();
        iniciarJuego();
        
        
    }
    
    public void iniciarJuego(){
        HistoricoControlador hisCon;
        hisCon = new HistoricoControlador(); //objeto para llamar método consultar datos del usuario ingresado en la base de datos
        his = hisCon.consultarCon(); //consultar datos del usuario ingresado en la base de datos mediante la clase controladora
        RondaControlador ronCon = new RondaControlador(); //objeto para llamar método que permite consultar datos de nivel y premios disponibles en la base de datos         
        rondaList = ronCon.consultarCon(); //se cargan los datos de niveles y premios en un arraylist de tipo ronda.
        ronActual.setNivel(0);
        ronActual.setPremio(0);
        inicializarRonda();
    }
    public void inicializarRonda(){

        boolean status = true; //permite saber cuando finalizar el juego
        jLabel1.setText(his.getJugador());
        ejecutarRonda(ronActual.getNivel()+1);
        status = false;
        jLabel2.setText("RONDA ACTUAL " +String.valueOf(ronActual.getNivel()+1)); //Establece la ronda
        jLabel4.setText("PUNTOS GANADOS " + String.valueOf(ronActual.getPremio()));
        jTextField1.setText(String.valueOf(rondaList.get(ronActual.getNivel()).getPremio()));
        
    }   
    public void ejecutarRonda (int rondaActual){
        CategoriaControlador categoriaCon = new CategoriaControlador();
        PreguntaControlador preguntaCon = new PreguntaControlador();
        OpcionControlador opcionCon = new OpcionControlador();
        System.out.println("ronda Actual " + rondaActual);
        ArrayList<Categoria> listaCategoria = categoriaCon.consultar(rondaActual);  
        System.out.println("cantidad de categorias: " + listaCategoria.size());
        System.out.println("Tipo de categoria: " + listaCategoria.get(0).getNombre());
        System.out.println("get id de ronda a la que pertenece: " + listaCategoria.get(0).getRonda_id());
        ArrayList <Pregunta> listaPregunta = preguntaCon.consultar(listaCategoria);
        int cantidadPregunta = listaPregunta.size(); //permite contabilizar las preguntas para la ronda o nivel actua
        System.out.println("cantidad de preguntas: " + cantidadPregunta );
        System.out.println("pregunta aleatorea"+(int) (Math.random()*cantidadPregunta));
        for (int i = 0; i < 10; i++) {
            System.out.println("pregunta aleatorea"+(int) (Math.random()*cantidadPregunta));
        }
        int indexPreguntaLista = (int) (Math.random()*cantidadPregunta);
        
        int preguntaID= listaPregunta.get(indexPreguntaLista).getId();
        for (int i = 0; i < listaPregunta.size(); i++) {
            System.out.println(listaPregunta.get(indexPreguntaLista).getEnunciado());
        }
        
        listaOpciones = opcionCon.consultar(preguntaID);
        jTextArea1.setText(listaPregunta.get(indexPreguntaLista).getEnunciado());
        jButton1.setText(listaOpciones.get(0).getRespuesta());
        jButton2.setText(listaOpciones.get(1).getRespuesta());
        jButton3.setText(listaOpciones.get(2).getRespuesta());
        jButton4.setText(listaOpciones.get(3).getRespuesta());
    }
        
    public void cargarAvance(){
        ronActual.setPremio(ronActual.getPremio()+ rondaList.get(ronActual.getNivel()).getPremio());
        his.setPremio(ronActual.getPremio());
        his.setRonda_alcanzada(ronActual.getNivel()+1);
        if ((his.getRonda_alcanzada())<ronda_maxima){
            ronActual.setNivel(his.getRonda_alcanzada());
            JOptionPane.showMessageDialog(null, "Has ganado la ronda, pasas a la siguiente","mensaje", JOptionPane.INFORMATION_MESSAGE);
            inicializarRonda();
        }else{            
            his.setClasificacion("PREMIO MAYOR");
            finalizarJuego();
        }
    }
    
    public void finalizarJuego(){
        switch (his.getClasificacion()){
            case "PREMIO MAYOR":
                JOptionPane.showMessageDialog(null, "HAS GANADO EL JUEGO","mensaje", JOptionPane.INFORMATION_MESSAGE);
                guardarHistorico();
                JF_Ganador jfGanador = new JF_Ganador();
                jfGanador.setLocationRelativeTo(null);
                jfGanador.setResizable(false);
                jfGanador.setVisible(true);
                dispose();
                break;
            case "ABANDONA JUEGO":
                JOptionPane.showMessageDialog(null, "Te has Retirado del Juego","mensaje", JOptionPane.INFORMATION_MESSAGE);
                guardarHistorico();
                JF_Retirado jfr = new JF_Retirado();
                jfr.setLocationRelativeTo(null);
                jfr.setResizable(false);
                jfr.setVisible(true);
                dispose();
                break;
            case "PERDIO EL JUEGO":
                his.setPremio(0);               
                JOptionPane.showMessageDialog(null, "PERDIO el juego","mensaje", JOptionPane.INFORMATION_MESSAGE);
                guardarHistorico();
                GameOver go= new GameOver();
                go.setLocationRelativeTo(null);
                go.setResizable(false);
                go.setVisible(true);
                dispose();
                break;
        }
    }
        
    public void guardarHistorico(){
        HistoricoControlador hisCon = new HistoricoControlador();
        hisCon.insertarHistorico(his);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Nombre del Jugador");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Ronda 1");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setOpaque(true);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jSeparator1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N

        jLabel4.setBackground(new java.awt.Color(204, 204, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Puntos ");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setOpaque(true);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(153, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(51, 51, 51));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Cual es la ciudad más grande de colombia");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setText("BOGOTÁ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 255));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton2.setText("BOGOTÁ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 204, 255));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton3.setText("BOGOTÁ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 204, 255));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton4.setText("BOGOTÁ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 153, 102));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton5.setText("RETIRARSE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Puntos  a ganar en la ronda actual");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField1.setText("100");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(440, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addGap(31, 31, 31)
                .addComponent(jButton4)
                .addGap(106, 106, 106))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if (listaOpciones.get(0).isEs_correcto()){
            cargarAvance();            
        }else{
            his.setClasificacion("PERDIO EL JUEGO");
            finalizarJuego();
            
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (listaOpciones.get(1).isEs_correcto()){
            cargarAvance();   
        }else{
            his.setClasificacion("PERDIO EL JUEGO");
            finalizarJuego();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        if (listaOpciones.get(2).isEs_correcto()){
            cargarAvance(); 
        }else{
            his.setClasificacion("PERDIO EL JUEGO");
            finalizarJuego();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (listaOpciones.get(3).isEs_correcto()){
            cargarAvance(); 
        }else{
            his.setClasificacion("PERDIO EL JUEGO");
            finalizarJuego();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        his.setClasificacion("ABANDONA JUEGO");
        finalizarJuego();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JF_Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_Level().setVisible(true);
            }
        });
    }
    class BackgroundPanel extends JPanel{ //inner class
        
        private Image imagen;
        
        @Override
        public void paint (Graphics g){
        
            imagen = new ImageIcon(getClass().getResource("/Imagenes/2352.jpg")).getImage();
            g.drawImage(imagen,0,0, getWidth(), getHeight(), this);
            setOpaque(false); 
            super.paint(g);
        }   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


    
}
