/* @author Jason Duffey
 * @version 1.4 - 2-18-14
 *
 * A GUI version of tic-tac-toe. Player 1 alsways X, Player 2 always O
 * Players switch who goes first each turn
*/

package tictacdoh;

import javax.swing.JOptionPane;
import javax.swing.JButton;

/**
 *
 * @author Jason Duffey
 */
public class TicTacDoh extends javax.swing.JFrame {
    private static Board myGameBoard = new Board();
    private JButton boardBtns[] = new JButton[9];
    private boolean firstPlayer = false; //toggles who goes first each time (true == player1)
    private boolean playerTurn = true; //toggles player turn during the game (true == player1)
    private int turnCounter = 0;
    
    private int player1Wins = 0;
    private int player2Wins = 0;
    private int ties = 0;
        
    /**
     * Creates new form TicTacDoh
     */
    public TicTacDoh() {
        initComponents();
        setPlayerNames();
        boardBtns[0] = Btn1;
        boardBtns[1] = Btn2;
        boardBtns[2] = Btn3;
        boardBtns[3] = Btn4;
        boardBtns[4] = Btn5;
        boardBtns[5] = Btn6;
        boardBtns[6] = Btn7;
        boardBtns[7] = Btn8;
        boardBtns[8] = Btn9;
        newGame();
    }
    
    /**
     * Sets the two players names for the game
     */
    public void setPlayerNames () {
        String prompt1 = "Player1 (X) please enter your name: ";
        String prompt2 = "Player2 (O) please enter your name: ";
        String p1 = JOptionPane.showInputDialog(this, prompt1);
        String p2 = JOptionPane.showInputDialog(this, prompt2);
        player1Label.setText(p1 + " (X)");
        player2Label.setText(p2 + " (O)");
    }
    
    /**
     * Resets the board to make a new game, alternates the first player
     */
    public void newGame () {
        myGameBoard.resetBoard();
        for (JButton btn: boardBtns) {
            btn.setText("");
        }
        turnCounter = 0;
        
        firstPlayer = !firstPlayer;
        
        if (firstPlayer == true) {
            playerTurn = true;
            outputLabel.setText (player1Label.getText() + " your turn");
        }
        else {
            playerTurn = false;
            outputLabel.setText (player2Label.getText() + " your turn");
        }
    }
    
    /**
     * Executed when a button is clicked, goes through the process of a player's turn
     * @param position the position on the board of the button (1-9)
     * @param evt the action event created when the button is pushed. Used to pass 
     *  the button to place the mark in
     */
    public void playerTurn (int position, java.awt.event.ActionEvent evt ) {
       JButton myButton = (JButton) evt.getSource();
        //check if it is a legal move
       //does nothing if it is not a legal move
        if (myGameBoard.isLegalMove(position) == false) {
            outputLabel.setText("Not a legal Move.");
            return;
        }

       //add the players turn to the counter
       turnCounter++;
        
        //assign the appropriate player mark
        char myMark;
        if (playerTurn == true ) {
           myMark = Board.CROSS;
       }
       else {
           myMark = Board.CIRCLE;
       }
       //switch whose turn it is for the next time a player takes a turn
        playerTurn = !playerTurn;
        
       
        //make the mark if it is a legal move
        myGameBoard.putMarkAt(position, myMark);
        myButton.setText("" + myMark);
        
        //check for a player win
        if(myGameBoard.hasThreeInRow(myMark)) {
            gameWon(myMark);
            return;
        }
        
        //if the game ends in a tie
        if (turnCounter >= 9) {
            tieGame();
        }
        
        //get ready for the next player's turn
        if (playerTurn == true) {
            outputLabel.setText(player1Label.getText() + " your turn");
        }
        else {
            outputLabel.setText(player2Label.getText() + " your turn");
        }
    }
    /**
     * Executes the end game process.
     * @param playerMark the mark of the player who won the game
     */
    private void gameWon (char playerMark) {
        String winner = "";
        //increases the correct players win total
        if(playerMark == Board.CIRCLE) {
            player2Wins++;
            player2WinsLabel.setText("" + player2Wins);
            winner = player2Label.getText() + " Wins!\n";
        }
        else if (playerMark == Board.CROSS) {
            player1Wins++;
            player1WinsLabel.setText("" + player1Wins);
            winner = player1Label.getText() + " Wins!\n";
        }
           
        //prompt for a new game
        String prompt = winner + "Would you like to play again?";
        int choice = JOptionPane.showConfirmDialog(this, prompt, prompt, JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            newGame();
        }
        else {
            System.exit(1);
        }
    }
    
    /**
     * If the game ends in a tie.
     */
    private void tieGame () {                
        //increase the number of ties and display new number
        ties++;
        tiesLabel.setText("" + ties);
        
        //prompt for a new game
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
        outputLabel = new javax.swing.JLabel();
        Btn1 = new javax.swing.JButton();
        Btn2 = new javax.swing.JButton();
        Btn3 = new javax.swing.JButton();
        Btn5 = new javax.swing.JButton();
        Btn4 = new javax.swing.JButton();
        Btn6 = new javax.swing.JButton();
        Btn7 = new javax.swing.JButton();
        Btn8 = new javax.swing.JButton();
        Btn9 = new javax.swing.JButton();
        player2Label = new javax.swing.JLabel();
        player2WinsLabel = new javax.swing.JLabel();
        player1Label = new javax.swing.JLabel();
        player1WinsLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tiesLabel = new javax.swing.JLabel();
        backgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.setLayout(null);

        outputLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        outputLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outputLabel.setText("Welcom to Tic-Tac-D'oh");
        jPanel1.add(outputLabel);
        outputLabel.setBounds(50, 20, 289, 27);

        Btn1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn1.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn1.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn1.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn1);
        Btn1.setBounds(110, 60, 50, 36);

        Btn2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn2.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn2.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn2.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn2ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn2);
        Btn2.setBounds(190, 60, 50, 36);

        Btn3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn3.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn3.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn3.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn3ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn3);
        Btn3.setBounds(260, 60, 50, 36);

        Btn5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn5.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn5.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn5.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn5);
        Btn5.setBounds(190, 120, 50, 36);

        Btn4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn4.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn4.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn4.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn4ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn4);
        Btn4.setBounds(110, 120, 50, 36);

        Btn6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn6.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn6.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn6.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn6ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn6);
        Btn6.setBounds(260, 120, 50, 36);

        Btn7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn7.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn7.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn7.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn7);
        Btn7.setBounds(110, 170, 50, 36);

        Btn8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn8.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn8.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn8.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn8ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn8);
        Btn8.setBounds(190, 170, 50, 36);

        Btn9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn9.setMaximumSize(new java.awt.Dimension(50, 25));
        Btn9.setMinimumSize(new java.awt.Dimension(50, 25));
        Btn9.setPreferredSize(new java.awt.Dimension(50, 25));
        Btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn9ActionPerformed(evt);
            }
        });
        jPanel1.add(Btn9);
        Btn9.setBounds(260, 170, 50, 36);

        player2Label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        player2Label.setText("Player 2");
        jPanel1.add(player2Label);
        player2Label.setBounds(300, 240, 80, 15);

        player2WinsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        player2WinsLabel.setText("0");
        jPanel1.add(player2WinsLabel);
        player2WinsLabel.setBounds(340, 270, 7, 15);

        player1Label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        player1Label.setText("Player 1");
        jPanel1.add(player1Label);
        player1Label.setBounds(10, 240, 80, 15);

        player1WinsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        player1WinsLabel.setText("0");
        jPanel1.add(player1WinsLabel);
        player1WinsLabel.setBounds(50, 270, 7, 15);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Ties");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(190, 240, 23, 15);

        tiesLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tiesLabel.setText("0");
        jPanel1.add(tiesLabel);
        tiesLabel.setBounds(200, 270, 7, 15);

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tictacdoh/images/board.jpg"))); // NOI18N
        jPanel1.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 400, 310);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed
        playerTurn(1, evt);
    }//GEN-LAST:event_Btn1ActionPerformed

    private void Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn2ActionPerformed
        playerTurn(2, evt);
    }//GEN-LAST:event_Btn2ActionPerformed

    private void Btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn3ActionPerformed
        playerTurn(3, evt);
    }//GEN-LAST:event_Btn3ActionPerformed

    private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
        playerTurn(4, evt);
    }//GEN-LAST:event_Btn4ActionPerformed

    private void Btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5ActionPerformed
        playerTurn(5, evt);
    }//GEN-LAST:event_Btn5ActionPerformed

    private void Btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn6ActionPerformed
        playerTurn(6, evt);
    }//GEN-LAST:event_Btn6ActionPerformed

    private void Btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn7ActionPerformed
        playerTurn(7, evt);
    }//GEN-LAST:event_Btn7ActionPerformed

    private void Btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn8ActionPerformed
        playerTurn(8, evt);
    }//GEN-LAST:event_Btn8ActionPerformed

    private void Btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn9ActionPerformed
        playerTurn(9, evt);
    }//GEN-LAST:event_Btn9ActionPerformed

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
            java.util.logging.Logger.getLogger(TicTacDoh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacDoh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacDoh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacDoh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacDoh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn1;
    private javax.swing.JButton Btn2;
    private javax.swing.JButton Btn3;
    private javax.swing.JButton Btn4;
    private javax.swing.JButton Btn5;
    private javax.swing.JButton Btn6;
    private javax.swing.JButton Btn7;
    private javax.swing.JButton Btn8;
    private javax.swing.JButton Btn9;
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JLabel player1Label;
    private javax.swing.JLabel player1WinsLabel;
    private javax.swing.JLabel player2Label;
    private javax.swing.JLabel player2WinsLabel;
    private javax.swing.JLabel tiesLabel;
    // End of variables declaration//GEN-END:variables
}
