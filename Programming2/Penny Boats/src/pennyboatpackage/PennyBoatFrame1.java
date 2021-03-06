/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pennyboatpackage;

import java.awt.Color;

/**
 *
 * @author Jason Duffey
 * @version 1.2 4/22/14
 * 
 * Creates the frame on which the module is shown, adjusts the size of the boat
 * and the weight on it.
 */
public class PennyBoatFrame1 extends javax.swing.JFrame {

    /**
     * Creates new form PennyBoatFrame
     */
    public PennyBoatFrame1() {
        initComponents();
        this.setBackground(Color.GREEN);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        weightSlider = new javax.swing.JSlider();
        AreaSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        drawingBoard = new pennyboatpackage.DrawingBoard();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PennyBoat");
        setMinimumSize(new java.awt.Dimension(850, 675));
        setResizable(false);

        weightSlider.setMajorTickSpacing(1);
        weightSlider.setMaximum(735);
        weightSlider.setMinimum(100);
        weightSlider.setMinorTickSpacing(1);
        weightSlider.setValue(300);
        weightSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                weightSliderStateChanged(evt);
            }
        });

        AreaSlider.setMaximum(735);
        AreaSlider.setMinimum(100);
        AreaSlider.setValue(418);
        AreaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                AreaSliderStateChanged(evt);
            }
        });

        jLabel1.setText("Peso:");

        jLabel2.setText("Área de la superficie:");

        drawingBoard.setMaximumSize(new java.awt.Dimension(850, 575));
        drawingBoard.setMinimumSize(new java.awt.Dimension(850, 575));
        drawingBoard.setPreferredSize(new java.awt.Dimension(850, 575));

        javax.swing.GroupLayout drawingBoardLayout = new javax.swing.GroupLayout(drawingBoard);
        drawingBoard.setLayout(drawingBoardLayout);
        drawingBoardLayout.setHorizontalGroup(
            drawingBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        drawingBoardLayout.setVerticalGroup(
            drawingBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        jButton1.setText("Ayudar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setLabel("Explicación");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(drawingBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(weightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AreaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(10, 10, 10)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(drawingBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)
                        .addGap(5, 5, 5)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(weightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AreaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void weightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_weightSliderStateChanged
        int boxSize = weightSlider.getValue(); //integer for the pixel size to make the box
        
        //boxSize numbers chosen arbitrarily to achieve the best look, for
        // the force vectors drawn on the boat and box
//        switch (weightSlider.getValue()) {
//            case 0: boxSize = 100; break;
//            case 1: boxSize = 175; break;
//            case 2: boxSize = 250; break;
//            case 3: boxSize = 315; break;
//            case 4: boxSize = 385; break;
//            case 5: boxSize = 455; break;
//            case 6: boxSize = 525; break;
//            case 7: boxSize = 595; break;
//            case 8: boxSize = 665; break;
//            case 9: boxSize = 735; break;
//            default: boxSize = 100;
//        }
        
        //change the length of the box determined on the slider position
        drawingBoard.setboxLength(boxSize);
    }//GEN-LAST:event_weightSliderStateChanged

    private void AreaSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_AreaSliderStateChanged
        int boatWidth = AreaSlider.getValue(); //integer for the pixel size to make the boat
        
        //boatWidth numbers chosen arbitrarily to achieve the best look, for
        // the force vectors drawn on the boat and box
//        switch (AreaSlider.getValue()) {
//            case 0: boatWidth = 100; break;
//            case 1: boatWidth = 175; break;
//            case 2: boatWidth = 250; break;
//            case 3: boatWidth = 315; break;
//            case 4: boatWidth = 385; break;
//            case 5: boatWidth = 455; break;
//            case 6: boatWidth = 525; break;
//            case 7: boatWidth = 595; break;
//            case 8: boatWidth = 665; break;
//            case 9: boatWidth = 735; break;
//            default: boatWidth = 100;
//        }
        
        drawingBoard.setBoatLength(boatWidth);
    }//GEN-LAST:event_AreaSliderStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //pop up the health dialog box
        HelpDialog help = new HelpDialog(this, false);
        help.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //pop up the theory dialog box
        TheoryDialog theory = new TheoryDialog(this, false);
        theory.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(PennyBoatFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PennyBoatFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PennyBoatFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PennyBoatFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PennyBoatFrame1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider AreaSlider;
    private pennyboatpackage.DrawingBoard drawingBoard;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider weightSlider;
    // End of variables declaration//GEN-END:variables
}
