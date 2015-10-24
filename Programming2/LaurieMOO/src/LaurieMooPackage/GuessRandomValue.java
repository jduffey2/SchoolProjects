/*
 * @author Jason Duffey
 * @version 1.7 - 2/4/14
 * 
 * Guess random value class for Lauriemoo. Generates a number and gets the number
 * of Big moos and little moos.
 */

package LaurieMooPackage;

/**
 *
 * @author Jason
 */
public class GuessRandomValue {
    
    private java.util.Random rndGenerator = new java.util.Random();
    private int randomValue;
    public final static int NUMBER_OF_VALUES = 10000;
    
    /**
     * Sets the answer to the game to the next pseudorandom positive number less than 10000.
     */
    public void setAnswer() {
        randomValue = rndGenerator.nextInt(NUMBER_OF_VALUES);
    }
    
    /**
     * Set the answer to a user given number.
     * @param n - a positive number less than 10000 to be used as the answer to be guessed.
     */
    public void setAnswer (int n) {
        if (n < 0 || n >= NUMBER_OF_VALUES)
            return; //take no action - value out of range
        randomValue = n;
    }
    
    /**
     * Returns the four digit number that is the answer
     * @return a string that is used as the answer
     */
    public String getAnswer () {
        String answer = Integer.toString(randomValue);
        int length = answer.length();
        if(length < 1)
            answer = "0" + answer;
        if(length < 2)
            answer = "0" + answer;
        if(length < 3)
            answer = "0" + answer;
        if(length < 4)
            answer = "0" + answer;
        return answer;
    }
    
    /**
     * Calculates how many numbers the person has guessed in the correct position
     * @param guess - the player's guess that is to be compared to the answer
     * @return - integer number of digits that the player has guessed correctly
     */
    public int getBigMooCount (int guess) {
        int answer = randomValue;
        int numBigMoos = 0;
        int guessArray[] = new int[4];
        int answerArray[] = new int[4];
        
        //break the guess and answer into 2 four-digit arrays
        for (int i = 0; i < 4; i++) {
            guessArray[i] = guess % 10;
            answerArray[i] = answer % 10;
            guess = guess / 10;
            answer = answer / 10;
        }
       //checks if the corresponding digits in the answer and guess are identical
        for (int i = 0; i < 4; i++) {
            if(guessArray[i] == answerArray[i]) {
                numBigMoos++;
            }
        }
        return numBigMoos;
    }
    
    /**
     * The number digits the player has guessed correctly not in the right position.
     * @param guess - the players guess that is to be compared to the answer
     * @return - integer number of digits the player has guessed correctly
     */
    public int getLittleMooCount (int guess) {
        int answer = randomValue;
        int numLilMoos = 0;
        int guessArray[] = new int[4];
        int answerArray[] = new int[4];
        
        //break the guess and answer into 2 four-digit arrays
        for (int i = 0; i < 4; i++) {
            guessArray[i] = guess % 10;
            answerArray[i] = answer % 10;
            guess = guess / 10;
            answer = answer / 10;
        } 
        
        //have to check if the numbers match up exactly resulting in a big moo
        // changes the guess and answer to -1 so they will not match other digits
         for (int i = 0; i < 4; i++) {
            if(guessArray[i] == answerArray[i]) {
                answerArray[i] = -1;
                guessArray[i] = -1;
            }
        }

        //compares the digits if they are the same but not in the same position
        for (int i = 0; i < 4; i++) {
           //if the guess matches up exactly (from above code)it skips comparing it
            if (guessArray[i] == -1) {
                continue;
            }
            for (int j = 0 ; j < 4; j++){
                //if they are in the same position it skips
                if (j == i) {
                    continue;   
                }
                //if it finds a match sets the answer to 1 and increases little moo count
                if (guessArray[i] == answerArray[j]) {
                    answerArray[j] = -1;
                    numLilMoos++;
                    break;
                }
            }
        }
        return numLilMoos;
    }
    
    /**
     * Compares the player's guess to the correct answer.
     * @param guess - the player's guess to be compared to the answer
     * @return - boolean true if the guess matches the answer, false if not
     */
    public boolean isCorrectGuess (int guess) {
        if (guess == randomValue) {
            return true;
        }
        return false;
    }
}