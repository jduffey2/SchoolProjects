/*
 * @author Jason Duffey
 * @version 1.5 2-18-14
 *
 * Generates a new board for a game of tic-tac-toe. Handles all of the internalization
 * of the board.
 */

package testpackage;


/**
 *
 * @author Jason Duffey
 * @version 1.5 2-18-14
 *
 * Generates a new board for a game of Tic-Tac-Toe. Handles all of the internalization
 * of the board.
 */
public class Board {
    /**
     * Value for empty (blank) square 
     */
    public final static char BLANK = ' ';
    /**
     * Value for cell marked by circles player
     */
    public final static char CIRCLE = 'O';
    /**
     * Value for cell marked by crosses player
     */
    public final static char CROSS = 'X';
    
    private static char board[][];
    
    public Board() {
        board = new char[3][3];
       resetBoard();
    }
    
    /**
     * Resets the board to its initial status of all blank cells.
     */
    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = BLANK;
            }
        }
    }
    /** 
     * Used to retrieve the mark stored at the indicated cell location
     * @param location - integer marking the location on the board to be checked
     * @return the character that is in the passed location
     */
    public char getMarkAt (int location) {
          int row = (location -1) / 3;
          int col = (location -1) % 3;
        
        return board[row][col];
    }
    
    /**
     * Test for whether a mark can be played on the specified cell location 
     * @param location - location on the board to check for a mark
     * @return - true if a legal move was made, false if not
     */
    public boolean isLegalMove (int location) {
        char mark_at_spot = getMarkAt (location);
        if(mark_at_spot != CROSS && mark_at_spot != CIRCLE)
            return true;
        else
            return false;
    }
    
    /**
     * Makes a mark on the board at the indicated position. 
     * @param location - location on the board to place the mark
     * @param mark - the players mark to place on board
     * @return - I'm not exactly sure why this is returning a boolean
     */
    public boolean putMarkAt (int location, char mark) {
        int row = (location -1) / 3;
        int col = (location -1) % 3;
        
        board[row][col] = mark;
        return true;
    }
    
    /**
     * Looks for the three-in-a-row (across, up-and-down, and/or diagonally) 
     * indicative of a Tic-Tac-Toe win
     * @param mark - player mark to check if a 3 in a row has been made
     * @return - true if player has three in row, false if not
     */
    public boolean hasThreeInRow (char mark) {
        //check  for tic-tac-toe
        for (int check = 0; check < 3; check++) {
            //check rows
            if (board[check][0] == mark && board[check][1] == mark && board[check][2] == mark) {
                return true;
            }
            //check columns
            if (board[0][check] == mark && board[1][check] == mark && board[2][check] == mark) {
                return true;
            }      
        }
        //check diagonals
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
                return true;
            }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return true;
        }
        return false;
    }
}   
