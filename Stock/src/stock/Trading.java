/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import javax.swing.SwingUtilities;

/**
 *
 * @author Peter
 */

public class Trading extends javax.swing.JFrame{
private boolean _isBuy;
private boolean _isShort;
    /**
     * Creates new form Trading
     */
        public Trading(User u) {
        _isBuy = false;
        _isShort = false;
        initComponents(u);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(User player) {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Buy = new javax.swing.JRadioButton();
        Short = new javax.swing.JRadioButton();
        Ticker = new javax.swing.JTextField();
        Submit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Amount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 23, 400, 300));
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(null);

        buttonGroup1.add(Buy);
        Buy.setFont(new java.awt.Font("YuMincho +36p Kana", 0, 24)); // NOI18N
        Buy.setForeground(new java.awt.Color(255, 255, 255));
        Buy.setText("Buy");
        Buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyActionPerformed(evt);
            }
        });
        getContentPane().add(Buy);
        Buy.setBounds(140, 170, 100, 42);

        Short.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(Short);
        Short.setFont(new java.awt.Font("YuMincho +36p Kana", 0, 24)); // NOI18N
        Short.setForeground(new java.awt.Color(255, 255, 255));
        Short.setText("Short");
        Short.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShortActionPerformed(evt);
            }
        });
        getContentPane().add(Short);
        Short.setBounds(140, 200, 90, 42);

        Ticker.setText("TICKER");
        Ticker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TickerActionPerformed(evt);
            }
        });
        getContentPane().add(Ticker);
        Ticker.setBounds(50, 170, 70, 26);

        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt, player);
            }
        });
        getContentPane().add(Submit);
        Submit.setBounds(230, 200, 88, 29);

        jLabel2.setFont(new java.awt.Font("YuMincho +36p Kana", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MAKE A TRADE");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 40, 460, 70);

        Amount.setText("NUMBER");
        getContentPane().add(Amount);
        Amount.setBounds(50, 200, 70, 26);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/background1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 290);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyActionPerformed
        // TODO add your handling code here:
        _isBuy = true;
        _isShort = false;

    }//GEN-LAST:event_BuyActionPerformed

    private void TickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TickerActionPerformed
        // TODO add your handling code here:                  
    }//GEN-LAST:event_TickerActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt, User player) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:

      if(_isBuy) {
       try {
             player.ExecuteBuy(Ticker.getText(),Integer.parseInt(Amount.getText()));

       }   
       catch (Exception e) {
           new BAD().setVisible(true);

       }
      }
      else if(_isShort) {
        player.ExecuteShort(Ticker.getText(),Integer.parseInt(Amount.getText()));
      }
      else {
           new BAD().setVisible(true);

      }      
    }//GEN-LAST:event_SubmitActionPerformed

    private void ShortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShortActionPerformed
        // TODO add your handling code here:
        _isBuy = false;
        _isShort = true;     
    }//GEN-LAST:event_ShortActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(User u) {
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
            java.util.logging.Logger.getLogger(Trading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trading(u).setVisible(true);
            }
        }); 
    }
public  void test() {
    repaint();
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Amount;
    private javax.swing.JRadioButton Buy;
    private javax.swing.JRadioButton Short;
    private javax.swing.JButton Submit;
    private javax.swing.JTextField Ticker;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
