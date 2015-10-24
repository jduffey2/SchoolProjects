/*
 * @author Jason Duffey
 * @version 1.3 3/12/14
 *
 * The scoreCard class for Yahtzee program, handles all scoring for yahtzee game.
 * Yahtzee is a registered trademark of Hasbro Inc. For educational purposes only.
 * Not for sale.
 */

package yahtzeepackage;

import java.util.ArrayList;
import dicetestpackage.DieLabel;

/**
 *
 * @author Jason Duffey
 */
public class ScoreCard {
    private int totalPlayerScore = 0;
    private int topHalfScore = 0;
    private int bottomHalfScore = 0;
    private int yahtzeeBonus = 0;
    private boolean hasBonus = false;
    
    /**
     * Clear scores back to zero for a new game
     */
    public void clearScores() {
        totalPlayerScore = 0;
        topHalfScore = 0;
        bottomHalfScore = 0;
        yahtzeeBonus = 0;
        hasBonus = false;
    }
    
    /**
     * adds specified number of points to the total score.
     * @param added the number of points to be added to total score
     */
    public void addToTotalScore(int added) {
        totalPlayerScore += added;
    }
    
    /**
     * returns the players total score
     * @return - the players points
     */
    public int getTotalScore () {
        return totalPlayerScore;
    }
    
    /**
     * returns the players score for the top half of the scoreCard
     * @return players points for the top half of the scorecard
     */
    public int getTopScore () {
        return topHalfScore;
    }
    
    /**
     * returns the players score for the bottom half of the scoreCard
     * @return players points for the bottom half of the scorecard
     */
    public int getBottomScore () {
        return bottomHalfScore;
    }
    
    /**
     * returns the players yahtzee bonus score.
     * @return players points they have for yahtzee bonuses.
     */
    public int getBonusScore () {
        return yahtzeeBonus;
    }
    
    /**
     * scores the value of only the dice with the value of the passed value
     * @param value_Scored the value of the dice to be scored
     * @param diceArray the dice to execute the scoring on
     * @return the score computed for the scoring combination
     */
    public int scoreAsNumber ( int value_Scored, ArrayList<DieLabel> diceArray) {
        int score = 0;
        if(checkYahtzeeBonus(diceArray)) {
            if(diceArray.get(0).getDieValue() != value_Scored) {
                score = 0;
            }
            else {
                score = scoreChance(diceArray);
            }
        }
        else {
            for (DieLabel die: diceArray) {
                if(die.getDieValue() == value_Scored) {
                    score += value_Scored;
                }
            }
        }
        topHalfScore += score;
        addToTotalScore(score);
        return score;
    }
    
    /**
     * scores the dice as either 3 or 4 of a kind
     * @param number the number of dice that need to have the same value
     * @param diceArray the dice to be scored
     * @return the players score for the scoring combination
     */
    public int scoreAsAKind ( int number, ArrayList<DieLabel> diceArray) {
        int score = 0; //their score for the three or four of a kind

        if(checkYahtzeeBonus(diceArray) == true) {
            score = scoreChance(diceArray);
        }
        else {
            int numSame = 1; //the number of dice that have the same value
            int valueSame; //the value of the die that is being compared to
            //determine if player has more than three of a kind
            //loop throw all die to determine if more than three match in value
            for (int i = 0; i < diceArray.size(); i++) {
                numSame = 1;
                valueSame = diceArray.get(i).getDieValue();
                for(int j = i + 1; j < diceArray.size(); j++) {
                    if (diceArray.get(j).getDieValue() == valueSame) {
                        numSame++;
                    }
                }
                if (numSame >= number) {
                    break;
                }
            }
            //return 0 if they do not have a valid combo
            if(numSame < number) {
                return 0;
            }

            //if here they have a valid combo
            for (DieLabel die: diceArray) {
                score += die.getDieValue();
            }
        }
        bottomHalfScore += score;
        addToTotalScore(score);
        return score;
    }
    
    /**
     * checks if the dice have a Yahtzee
     * @param diceArray the dice to check for a yahtzee
     * @return true if the dice are a yahtzee, false if not
     */
    private boolean isYahtzee (ArrayList<DieLabel> diceArray) {
        int compareValue = diceArray.get(0).getDieValue();
        for(int i = 1; i < diceArray.size(); i++) {
            if(diceArray.get(i).getDieValue() != compareValue) {
                return false;
            }
        } 
        return true;
    }
    
    /**
     * scores the dice for a yahtzee and allows for bonuses to accrue
     * @param diceArray the dice to check the yahtzee with
     * @return the player's score for a yahtzee (50 if yahtzee or 0)
     */
    public int scoreYahtzee(ArrayList<DieLabel> diceArray) {
        if(isYahtzee(diceArray)) {
            hasBonus = true;
            bottomHalfScore += 50;
            addToTotalScore(50);
            return 50;
        }
        else
            return 0;
    }
    
    /**
     * score the dice a a straight
     * @param number number of dice that need to be in the straight
     * @param diceArray the dice to check
     * @return the players score for the straight scoring combos
     */
    public int scoreAsStraight (int number, ArrayList<DieLabel> diceArray) {
        int numIn = 1; //number of die that are "in" the straigt
        if(checkYahtzeeBonus(diceArray) == true) {
            if(number == 5) {
                numIn = 5;
            }
            else {
                numIn = 4;
            }
        }
        else {
            int values[] = new int[diceArray.size()];
            //place value of the dice into a new array
            for (int i = 0; i < diceArray.size(); i++) {
                values[i] = diceArray.get(i).getDieValue();
            }

            //sort the values into ascending order
            int temp;
            for (int i = 0; i < values.length; i++) {
                for (int j = i + 1; j < values.length; j++) {
                    if (values[j] < values[i]) {
                        temp = values[i];
                        values[i] = values[j];
                        values[j] = temp;
                    }
                }
            }

            //determine if they have a straight
            for (int i = 1; i < values.length; i++) {
                //break out for a small straight if one is found
                if (number == 4 && numIn == 4) {
                    break;
                }
                //ignore if there is a repeated number
                if (values[i] == values[i - 1]) {
                    continue;
                }
                //check if the current number is one more than the previous number
                if (values[i] == values[i - 1] + 1) {
                    numIn++;
                } else {
                    numIn = 1;
                }
            }
        }
        if(numIn == 5 && number == 5) {
            bottomHalfScore += 40;
            addToTotalScore(40);
            return 40;
        }
        if(numIn >= 4 && number == 4) {
            bottomHalfScore += 30;
            addToTotalScore(30);
            return 30;
        }

        return 0;
    }
    
    /**
     * socres the dice as a full house
     * @param diceArray the dice to check
     * @return the player's score after full house scoring
     */
    public int scoreFullHouse (ArrayList<DieLabel> diceArray) {
        int value1; //the value of the the first set of die
        int value2 = 0; //the value of the second set of die
        int numOf1 = 1; //number of die in set 1
        int numOf2 = 1; //number of die in set 2
        boolean second = false; //determines if value2 has been assigned yet

        if(checkYahtzeeBonus(diceArray)) {
            numOf1 = 3;
            numOf2 = 2;
        }
        else {
            int values[] = new int[diceArray.size()];
            //place value of the dice into a new array
            for (int i = 0; i < diceArray.size(); i++) {
                values[i] = diceArray.get(i).getDieValue();
            }

            //determine how many dice fall into each of the two sets of values
            value1 = values[0];
            for (int i = 1; i < values.length; i++) {
                if (values[i] == value1) {
                    numOf1++;
                    continue;
                } else if (second == false) {
                    value2 = values[i];
                    second = true;
                    continue;
                }

                if (values[i] == value2) {
                    numOf2++;
                }
            }
        }
        //determine if an appropriate number of die fall into the two sets of values
        //coded as two different if statements for readability
        if(numOf1 == 3 && numOf2 == 2 ) {
            bottomHalfScore += 25;
            addToTotalScore(25);
            return 25;
        }
        if(numOf1 == 2 && numOf2 == 3) {
            bottomHalfScore += 25;
            addToTotalScore(25);
            return 25;
        }
        return 0;
    }
    
    /**
     * scores the dice as chance - adds the total of all dice
     * @param diceArray the dice to score
     * @return the players score for a "chance" combo
     */
    public int scoreChance (ArrayList<DieLabel> diceArray) {
        int score = 0;
        checkYahtzeeBonus(diceArray);
        for (DieLabel die: diceArray) {
            score += die.getDieValue();
        }
        addToTotalScore(score);
        return score;
    }
    
    /**
     * checks for yahtzee bonuses, adds score to players score
     * @param diceArray the dice to check
     * @return true if they have the bonus, false if not
     */
    private boolean checkYahtzeeBonus(ArrayList<DieLabel> diceArray) {
        if(isYahtzee(diceArray) && hasBonus) {
            yahtzeeBonus += 100;
            addToTotalScore(100);
            return true;
        }
        return false;
    }
}