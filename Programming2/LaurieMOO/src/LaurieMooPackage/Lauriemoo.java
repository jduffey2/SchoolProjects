/*
 * @author Jason Duffey
 * @version 1.7 - 2/4/14
 * 
 * The famous game of LaurieMOO! (Trademark free Mastermind).
 * Guess four numbers and their position in a four digit sequence. Ten turns
 * to guess the number. And only feed back if whether they have a number in the
 * right position, and a number in the wrong position.
 */

package LaurieMooPackage;

import javax.swing.JOptionPane;
/**
 *
 * @author Jason
 */
public class Lauriemoo extends javax.swing.JFrame {
    private GuessRandomValue gameNumber = new GuessRandomValue();
    private static int num_Of_Guesses = 0;
   
    /**
     * Creates new form Lauriemoo
     */
    public Lauriemoo() {
        initComponents();
        newGame();
    }
    
    private void newGame () {
        //set all values to default values and generate a new answer
        gameNumber.setAnswer();
        num_Of_Guesses = 0;
        pastGuessText.append("");
        guessLabel.setText("Guess #" + (num_Of_Guesses + 1));
        guessTextField.setText("????");
        laurietext1.setText("");
        laurietext2.setText("");
        laurietext3.setText("");
        laurietext4.setText("");
        winMooTextField.setText("");
        pastGuessText.setText("");
        guessTextField.setEditable(true);
    }
    
    //asks the user if they want to play again, and takes the appropriate actions
    private void repeatGame() {
        String prompt = "Would you like to play again?";
        int choice = JOptionPane.showConfirmDialog(this, prompt, prompt, JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            newGame();
        }
        else {
            System.exit(1);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pastGuessText = new javax.swing.JTextArea();
        guessLabel = new javax.swing.JLabel();
        guessTextField = new javax.swing.JTextField();
        winMooTextField = new javax.swing.JLabel();
        laurietext1 = new javax.swing.JLabel();
        laurietext2 = new javax.swing.JLabel();
        laurietext3 = new javax.swing.JLabel();
        laurietext4 = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.setLayout(null);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pastGuessText.setEditable(false);
        pastGuessText.setColumns(20);
        pastGuessText.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        pastGuessText.setRows(5);
        pastGuessText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pastGuessTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pastGuessTextKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(pastGuessText);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(300, 10, 69, 110);

        guessLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        guessLabel.setText("Guess # 1");
        jPanel1.add(guessLabel);
        guessLabel.setBounds(10, 20, 56, 14);

        guessTextField.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        guessTextField.setText(" ");
        guessTextField.setMinimumSize(new java.awt.Dimension(54, 33));
        guessTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guessTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(guessTextField);
        guessTextField.setBounds(80, 10, 67, 30);

        winMooTextField.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        winMooTextField.setForeground(new java.awt.Color(255, 255, 255));
        winMooTextField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winMooTextField.setMinimumSize(new java.awt.Dimension(200, 25));
        jPanel1.add(winMooTextField);
        winMooTextField.setBounds(30, 250, 333, 25);

        laurietext1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(laurietext1);
        laurietext1.setBounds(60, 180, 40, 30);

        laurietext2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(laurietext2);
        laurietext2.setBounds(130, 200, 40, 30);

        laurietext3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(laurietext3);
        laurietext3.setBounds(210, 150, 40, 30);

        laurietext4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        laurietext4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(laurietext4);
        laurietext4.setBounds(290, 200, 40, 30);

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cows.jpg"))); // NOI18N
        jPanel1.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 400, 300);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guessTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guessTextFieldActionPerformed

        //retrieve the guess and convert it into an integer
        winMooTextField.setText("");
        String guess = guessTextField.getText();
        
        //simple error checking
        int intGuess;
        try {
            intGuess = Integer.parseInt(guess);
        }
        catch (NumberFormatException e) {
            winMooTextField.setText(guess + " is not a valid int");
            guessTextField.setText("");
            return;
        }
        if(intGuess >= 10000 || intGuess < 0) {
            winMooTextField.setText(guess + " is not an appropriate number");
            return;
        }
        
       //remove the previous turns moos from the board
        laurietext1.setText("");
        laurietext2.setText("");
        laurietext3.setText("");
        laurietext4.setText("");
        
//add the guess to the total number of guesses
        num_Of_Guesses++;
        
        guessLabel.setText("Guess #" + (num_Of_Guesses + 1));
        guessTextField.setText("");
        pastGuessText.append(guess + "\n" );
        
        int bigMooCount = gameNumber.getBigMooCount(intGuess);
        int littleMooCount = gameNumber.getLittleMooCount(intGuess);
        
        //display the big MOOS
        //begins filling in the big MOOS in the boxes left to right
        if (bigMooCount >= 1) {
            laurietext1.setText("MOO!");
        }
        if (bigMooCount >= 2) {
            laurietext2.setText("MOO!");
        }
        if (bigMooCount >=3) {
            laurietext3.setText("MOO!");
        }
        if (bigMooCount ==4) {
            laurietext4.setText("MOO!");
        }
        
        //display the little moos
        //begins filling in the small moos in the boxes right to left
        if (littleMooCount >= 1) {
            laurietext4.setText("moo.");
        }
        if (littleMooCount >= 2) {
            laurietext3.setText("moo.");
        }
        if (littleMooCount >= 3) {
            laurietext2.setText("moo.");
        }
        if (littleMooCount == 4) {
            laurietext1.setText("moo.");
        }
        
                //handles if they guess correctly
        if(gameNumber.isCorrectGuess(intGuess)) {
            winMooTextField.setText("LaurieMOO!!!");
            guessTextField.setText(gameNumber.getAnswer());
            guessTextField.setEditable(false);
            repeatGame();
            return;
        }
        
        //if they lose the game
        if(num_Of_Guesses >= 10) {
            winMooTextField.setText("Boo hoo -- no LaurieMOO");
            guessTextField.setText(gameNumber.getAnswer());
            guessTextField.setEditable(false);
            repeatGame();
            return;
        }
    }//GEN-LAST:event_guessTextFieldActionPerformed

    private void pastGuessTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pastGuessTextKeyPressed
        char c = evt.getKeyChar();
        if(c == 'J')
            winMooTextField.setText(gameNumber.getAnswer());
    }//GEN-LAST:event_pastGuessTextKeyPressed

    private void pastGuessTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pastGuessTextKeyReleased
        winMooTextField.setText("");
    }//GEN-LAST:event_pastGuessTextKeyReleased

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
            java.util.logging.Logger.getLogger(Lauriemoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lauriemoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lauriemoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lauriemoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lauriemoo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel guessLabel;
    private javax.swing.JTextField guessTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel laurietext1;
    private javax.swing.JLabel laurietext2;
    private javax.swing.JLabel laurietext3;
    private javax.swing.JLabel laurietext4;
    private javax.swing.JTextArea pastGuessText;
    private javax.swing.JLabel winMooTextField;
    // End of variables declaration//GEN-END:variables
}