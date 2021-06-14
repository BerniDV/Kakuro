/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Presentacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import kakuro.Dades.CtrlDades;
import kakuro.pair.Pair;
    
/**
 *
 * @author rhoms
 */
public class TaulerView extends javax.swing.JFrame implements ActionListener {
    private CtrlPresentacio CP;
    private Pair[][] pmat;
    private final JTextField[][] Text;
    private Pair[][] matriuSolucionada;
    private JLabel info;
     private JPanel mainPanel;
      private JPanel windowPanel;
      private JPanel buttonPanel;
      private JButton check;
      private JButton reset;
      private JButton hint;
       private JButton save_repositori;
      private JButton save_exit;
      private int pistes_donades;
      
  
    public TaulerView(CtrlPresentacio CPR) throws IOException {
        this.CP=new CtrlPresentacio();
       this.CP=CPR;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Kakuro");
	this.setMinimumSize(new Dimension(900,600));
        this.pistes_donades=0;
        JMenuBar menuBar = new JMenuBar();
        JMenu Partida=new JMenu("Partida");
        
        menuBar.add(Partida);
        this.setJMenuBar(menuBar); 
       
     
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        this.pmat=this.CP.getTaulerPartida();
        this.matriuSolucionada=this.CP.getTaulerResolt();
        int files=pmat.length;
        int columnes=pmat[0].length;
        this.Text=new JTextField[files][columnes];
        this.windowPanel = new JPanel();
        
        windowPanel.setPreferredSize(new Dimension(600,500));
        
        
        for(int i=0;i<files;++i){
            for(int j=0;j<columnes;++j){
                 this.Text[i][j]=new JTextField();                
                 if(pmat[i][j].Negra()){
                     this.Text[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 18));
                     this.Text[i][j].setBackground(new Color(0.0f,0.0f,0.0f));
                     this.Text[i][j].setForeground(new Color(1.0f,1.0f,1.0f));
                     if(pmat[i][j].NegraNoBuida()){
                         
                         if(pmat[i][j].getValorColumna()>0 && pmat[i][j].getValorFila()>0){
                              this.Text[i][j].setText("C"+Integer.toString(pmat[i][j].getValorColumna())+"F"+Integer.toString(pmat[i][j].getValorFila()));
                              this.Text[i][j].setHorizontalAlignment(JTextField.CENTER);
                         }
                         else if(pmat[i][j].getValorColumna()>0 ){
                             this.Text[i][j].setText("C"+Integer.toString(pmat[i][j].getValorColumna()));
                             this.Text[i][j].setHorizontalAlignment(JTextField.CENTER);
                         }
                         else if(pmat[i][j].getValorFila()>0 ){
                             this.Text[i][j].setText("F"+Integer.toString(pmat[i][j].getValorFila()));
                             this.Text[i][j].setHorizontalAlignment(JTextField.RIGHT);
                         }
                            
                     }
                     this.Text[i][j].setEditable(false);
                     this.Text[i][j].setSize(50,50);  
                      windowPanel.add(this.Text[i][j]);
                      }
                     else if(pmat[i][j].Blanca()){
                        this.Text[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 18));
                        this.Text[i][j].setBackground(new Color(1.0f,1.0f,1.0f));
                        if(pmat[i][j].getValorBlanca()!=0)
                            this.Text[i][j].setText(Integer.toString(pmat[i][j].getValorBlanca()));
                        this.Text[i][j].setHorizontalAlignment(JTextField.CENTER);

                         windowPanel.add(this.Text[i][j]);
                 }
            }
        }
        this.windowPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
       this.windowPanel.setLayout(new GridLayout(files,columnes));
  
        this.mainPanel.add(windowPanel);
        
         this.buttonPanel = new JPanel();
         buttonPanel.setPreferredSize(new Dimension(200,100));
         //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
    
         this.check=new JButton("SUBMIT");
         this.check.addActionListener(this);
         check.setHorizontalAlignment(JButton.CENTER);    
         buttonPanel.add(check);
         this.reset=new JButton("RESET");
         this.reset.addActionListener(this);
          buttonPanel.add(reset);
          this.hint=new JButton("HINT");
         this.hint.addActionListener(this);
          buttonPanel.add(hint);
           this.save_repositori=new JButton("SAVE REPOSITORI");
         this.save_repositori.addActionListener(this);
          buttonPanel.add(save_repositori);
           this.save_exit=new JButton("SAVE PARTIDA & EXIT");
         this.save_exit.addActionListener(this);
          buttonPanel.add(save_exit);
          
         this.info=new JLabel("Kakuro");
         info.setFont(new Font("OCR A Extended", Font.BOLD, 16));
         this.info.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
              
         mainPanel.add(buttonPanel);
         mainPanel.add(info);
  
         this.add(mainPanel);
        this.setVisible(true);
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==check){
            Boolean error=false;
             for(int i=0;i<this.pmat.length;++i){
                 for(int j=0;j<this.pmat[0].length;++j){
                     if(this.matriuSolucionada[i][j].Blanca()){
                        if(!this.Text[i][j].getText().equals("")){ 
                          if(this.Text[i][j].getText().length()==1){
                             if(this.Text[i][j].getText().charAt(0)>'0' && this.Text[i][j].getText().charAt(0)<='9'){
                                if(Integer.parseInt(this.Text[i][j].getText())==this.matriuSolucionada[i][j].getValorBlanca()){
                                    this.Text[i][j].setBackground(new Color(0.0f,0.6f,0.0f));
                                }
                                else{
                                   // this.Text[i][j].setText(Integer.toString(matriuSolucionada[i][j].getValorBlanca()));
                                   error=true;
                                    this.Text[i][j].setBackground(new Color(0.6f,0.0f,0.0f));  
                                }
                             }       
                             else{
                                this.info.setText("Format Incorrecte en fila:"+i+" columna:"+j);  
                                this.Text[i][j].setBackground(new Color(0.6f,0.0f,0.0f)); 
                                error=true;
                                }  
                         }
                         else{
                                this.info.setText("Format Incorrecte en fila:"+i+" columna:"+j); 
                                this.Text[i][j].setBackground(new Color(0.6f,0.0f,0.0f)); 
                                error=true;
                             } 
                        }
                        else{
                            this.info.setText("Falten celes per omplir"); 
                           error=true;
                        }
                    }
                 }
             }
             if(!error){
                 int puntuacio = 0;
                  String temps = "";
                try {
                    puntuacio = this.CP.obtenirPuntuacio();
                    temps = this.CP.getTemps();
                } catch (IOException ex) {
                    Logger.getLogger(TaulerView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (temps.contains("0:0.000") ) this.info.setText("No pots obtenir puntuació en una partida que s'ha pausat");
                else this.info.setText("Kakuro Resolt! Temps: " + temps + " | Puntuació obtinguda: "+Integer.toString(puntuacio));
                
             }
            
        }
        else if(e.getSource()==reset){
            for(int i=0;i<this.pmat.length;++i){
                     for(int j=0;j<this.pmat[0].length;++j){
                         if(this.pmat[i][j].Blanca()){
                             this.Text[i][j].setText("");
                             this.Text[i][j].setBackground(new Color(1.0f,1.0f,1.0f));
                         }
                     }
             }
            this.info.setText("Kakuro");
        }
        else if(e.getSource()==hint){
            int h=0;
            for(int i=0;i<this.pmat.length;++i){
                for(int j=0;j<this.pmat[0].length && this.pistes_donades<3;++j){
                    if(this.Text[i][j].getText().equals("H") || this.Text[i][j].getText().equals("h")){
                        this.Text[i][j].setText(Integer.toString(matriuSolucionada[i][j].getValorBlanca()));
                        this.Text[i][j].setBackground(new Color(1.0f,1.0f,0.3f));
                        ++h;
                        ++this.pistes_donades;
     
                    }
                }
            }
            
            boolean pista=false;
            int count=0;
            Random r=new Random();
            int i,j;
            if(this.pistes_donades<3 && h==0){
                while(!pista && count<=20){
                    i=r.nextInt(this.pmat.length);
                    j=r.nextInt(this.pmat[0].length);
                     if(this.pmat[i][j].Blanca() && this.Text[i][j].getText().equals("")){
                          this.Text[i][j].setText(Integer.toString(matriuSolucionada[i][j].getValorBlanca()));
                          this.Text[i][j].setBackground(new Color(1.0f,1.0f,0.3f));
                          ++this.pistes_donades;
                          pista=true;
                     }
                    ++count;
                }
                if(count>20)
                    this.info.setText("No hi ha més pìstes disponibles.");
            }
            else{
                if(this.pistes_donades>=3)
                    this.info.setText("Límit de pistes superat.");
            }
        }
        else if(e.getSource()==save_repositori){
            PopUp pu = new PopUp(this.CP, this.pmat);
            pu.setVisible(true);
        }
        else if(e.getSource()==save_exit){
            try { 
                 for(int i=0;i<this.pmat.length;++i){
                 for(int j=0;j<this.pmat[0].length;++j){
                     if(this.matriuSolucionada[i][j].Blanca()){
                        if(!this.Text[i][j].getText().equals("")){ 
                            int a=this.Text[i][j].getText().charAt(0)-'0';
                           this.pmat[i][j] = new Pair(a);
                                  
                        }
                        else{
                            this.pmat[i][j] = new Pair(0);
                        }
                     }
                 }
                 }
                
                this.CP.GuardarPartidaEnCurs(this.pmat);
            } catch (IOException ex) {
                Logger.getLogger(TaulerView.class.getName()).log(Level.SEVERE, null, ex);
            }
     
       
            this.setVisible(false);
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
            java.util.logging.Logger.getLogger(TaulerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaulerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaulerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaulerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              /*  TaulerView tauler = null;
                
                try {
                    tauler = new TaulerView("Tauler4");
                } catch (IOException ex) {
                    Logger.getLogger(TaulerView.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                 
               // tauler.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
