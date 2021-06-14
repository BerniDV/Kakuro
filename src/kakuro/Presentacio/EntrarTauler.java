/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Presentacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import kakuro.pair.Pair;

/**
 *
 * @author rhoms
 */
public class EntrarTauler extends javax.swing.JFrame implements ActionListener {
        private JPanel window;
        private JTextField[][] T;
        private JButton B;
        private JPanel P;
        private CtrlPresentacio CP;
        private Pair[][] matriu;
        private JLabel info;
    /**
     * Creates new form EntrarTauler
     */
    public EntrarTauler(int files, int columnes,CtrlPresentacio cont) {
        this.matriu=new Pair[files][columnes];            
        this.CP=cont;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Entrar Kakuro");
	this.setMinimumSize(new Dimension(800,600));
        P=new JPanel();
        T=new JTextField[files][columnes];
        this.info=new JLabel("Cela Negra buida: * | Valor Columna X: CX | Valor Fila X: FX | Valor Columna X i Fila Y: CXFY");
        this.info.setFont(new Font("SansSerif", Font.PLAIN, 16));
        this.info.setBorder(BorderFactory.createLineBorder(Color.black,2,true));
        this.window=new JPanel();
        P.setLayout(new BoxLayout(P, BoxLayout.Y_AXIS));
        for(int i=0;i<files;++i){
            for(int j=0;j<columnes;++j){
                T[i][j]=new JTextField(4); 
                this.T[i][j].setFont(new Font("SansSerif", Font.PLAIN, 18));
                this.T[i][j].setHorizontalAlignment(JTextField.CENTER);
                this.window.add(T[i][j]);
            }
        }
        this.window.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        this.window.setLayout(new GridLayout(files,columnes));
        this.P.add(info);
        P.add(window);
        B=new JButton("CARREGA TAULER");
       // this.B.setHorizontalAlignment(JButton.CENTER);   
        this.B.addActionListener(this);
        this.P.add(this.B);      
        this.add(this.P);
        this.setVisible(true);
       
    }

    
        @Override
     public void actionPerformed(ActionEvent e){
        if(e.getSource()==B){
             for(int i=0;i<this.T.length;++i){
                 for(int j=0;j<this.T[0].length;++j){
                     int columna=0;
                     int fila=0;
                 
                     char ch2[]  = this.T[i][j].getText().toCharArray();
                     if(ch2.length>0){
                         if(ch2[0]=='*'){
                             matriu[i][j]= new Pair(0,0);
                         }
                         else if(ch2[0]=='C'){
                             if(ch2.length>2 ){
                                if((ch2[2]>='0') && (ch2[2]<='9')){
                                     columna=(ch2[1]-'0')*10+ch2[2]-'0';
                                }
                                else{
                                    columna=ch2[1]-'0';
                                }
                                if(ch2[2]=='F'){
                                    if(ch2.length>4 ){
                                       if((ch2[4]>='0') && (ch2[4]<='9')){
                                            fila=(ch2[3]-'0')*10+ch2[4]-'0';
                                        } 
                                    }
                                    else
                                        fila=ch2[3]-'0';
                                }
                                else if(ch2.length>4 && ch2[3]=='F'){
                                    if(ch2.length>5 ){
                                       if((ch2[5]>='0') && (ch2[5]<='9')){
                                            fila=(ch2[4]-'0')*10+ch2[5]-'0';
                                        } 
                                    }
                                    else
                                        fila=ch2[4]-'0';
                                }
                             
                            }
                             else{
                                 columna=ch2[1]-'0';
                             }
                             matriu[i][j]= new Pair(fila,columna);
                        }
                        else if(ch2[0]=='F'){
                              if(ch2.length>2 ){
                                if((ch2[2]>='0') && (ch2[2]<='9')){
                                     fila=(ch2[1]-'0')*10+ch2[2]-'0';
                                }
                               }
                               else{
                                    fila=ch2[1]-'0';
                                }
                              
                           matriu[i][j]= new Pair(fila,0);   
                        }
                        else{
                            matriu[i][j]=new Pair(0);
                        }
                     }
                     else
                         matriu[i][j]=new Pair(0);
                     
                  
                 }
             }
            try {
                if(CP.validarTauler(this.matriu)) {
                    CP.comencarPartidaActual();
                    this.setVisible(false);
                }
                else{
                    this.info.setText("Kakuro erroni");
                }
            } catch (IOException ex) {
                Logger.getLogger(EntrarTauler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     }

        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(EntrarTauler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntrarTauler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntrarTauler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntrarTauler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new EntrarTauler(7,7).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
