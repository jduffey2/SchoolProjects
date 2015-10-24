/* Name: MP2
* Date: 10 - 24 - 13
* Author: Jason Duffey
*
*Plays a player vs. computer game of pig, where a pair of dice is rolled.
*if a 1 shows up you lose all the points for the round, double 1 loses all
*points in a game, if not the sum of the two dice is added to the round score
*and the player has a choice of rolling again or stopping. First to 100 wins.
*/

#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

const int WIN_VALUE = 100; //value that the player and computer play to

int rollDie (void);
bool isTurnScoreLost (int die1value, int die2value);
bool isGameScoreLost (int die1value, int die2value);
char getUserInput (void);
void playerRound (int& score);
void cpuRound (int& score);
void checkWin (int player);


/* * * * * MAIN * * * * */


int main () 
{
	int cpuScore = 0; //keeps track of the computer's score
	int playerScore = 0; //keeps track of the player's score

	srand (time ((unsigned) 0));

	cout << "Welcome to pig!" << endl
		<< "You will be playing a game to 100, against the computer.\n\n";

	//loops player rounds, and computer rounds until there is a winner
	while (cpuScore < WIN_VALUE && playerScore < WIN_VALUE)
	{
		playerRound (playerScore);
		cout << "Your total game score is " << playerScore << ".\n\n";
		if (playerScore >= WIN_VALUE)
		{
			break;
		}
		cpuRound (cpuScore);
		cout << "Computer's total game score is " << cpuScore << ".\n\n";
	}
	
	//Determines who won the game
	checkWin (playerScore);
	return 0;
}


/* * * * * FUNCTION DEFINITIONS * * * * */


/*
 * int cpuRound: plays a round for the computer stops when computer has 20 points
 * Parameters: int score - passes the computers current game score
 * Return: the computers game score after the round
*/
void cpuRound (int& score)
{
	const int ROUND_STOP = 20; //the minimum value on which the computer will stop rolling
	
	int die1;
	int die2;
	int diceSum; //sum of the two dice on the current roll
	int roundScore = 0; //current points for the round

	//rolls the dice, checks for one or double one, then looks if it should roll again
	// (if the round sum is less then 20
	do
	{
		die1 = rollDie ();
		die2 = rollDie ();
		diceSum = die1 + die2;

		cout << "\n The computer rolls a " << die1 << " and " << die2 << ".\n";
		
		//checks for 1 or double 1
		if ( isGameScoreLost(die1, die2) )
		{
			score = 0;
			cout << "It rolled double 1!\n";
			return;
		}
		if ( isTurnScoreLost(die1, die2) )
		{
			roundScore = 0;
			cout << "It rolled a 1! It doesn't get points this round.\n";
			break;
		}
		
		//adds the sum of the dice to the current 
		roundScore += diceSum;
		cout << "Its current round total is now "
			<< roundScore << ".\n";
		if (score + roundScore >= WIN_VALUE)
		{
			break;
		}
	}
	while ( roundScore < ROUND_STOP );
	score += roundScore;
}

/*
* playerRound: plays through one round for the player
* Parameters: int score - passes the players current game score
* Return : players score for the round
*/
void playerRound (int& score)
{
	int die1;
	int die2;
	int diceSum; //sum of the two dice on the current roll
	int roundScore = 0; //current points for the round
	char roundStart; //stores the user's choice to roll the dice the first time

	//asks the user to roll the die for their first roll
	cout << endl <<  "It is your turn to roll again! (r to roll)...";
	cin >> roundStart;
	
	//rolls die, checks for 1 or double 1, adds points to round total, and 
	// repeats until the user tells it to stop
	do
	{
		die1 = rollDie ();
		die2 = rollDie ();
		diceSum = die1 + die2;

		cout << "\n You rolled a " << die1 << " and " << die2 << ".\n";
		
		//checks for 1 or double one
		if ( isGameScoreLost(die1, die2) )
		{
			
			score = 0;
			cout << "You rolled double 1!\n";
			return;
		}
		if ( isTurnScoreLost(die1, die2) )
		{
			roundScore = 0;
			cout << "Your rolled a 1! No points for this round.\n";
			break;
		}
		
		//if a 1 is not rolled
		roundScore += diceSum;
		cout << "You add " << diceSum << " to your round total, which is now "
			<< roundScore << ".\n";
	}
	while ( getUserInput () == 'r');
	score += roundScore;
}

/*
* rollDie: simulates the roll of a standard (6-sided) die.
* Parameters: none
* Returns: integer pip value (from 1 to 6) of the rolled die.
*/
int rollDie (void) 
{
	const int NUMBER_OF_DIE_SIDES = 6; //on a normal die
	const int LOWEST_DIE_VALUE = 1;

	return rand() % NUMBER_OF_DIE_SIDES + LOWEST_DIE_VALUE;
}

/*
* getUserInput: gets the input from the user on whether or not to roll again
* Parameters: none
* Returns: r or s depending on wheter the user wants to roll(r) or stop(s)
*/
char getUserInput (void)
{
	char playerChoice;

	//repeats prompt until a correct input is entered
	do
	{
		cout << "Do you wish to roll again or stop? (r to roll, s to stop): ";
		cin >> playerChoice;
	}
	while (playerChoice != 'r' && playerChoice != 'R' && playerChoice != 's' &&
		playerChoice != 'S');
	
	return tolower( playerChoice );
}

/*
* isTurnScoreLost: evaluates if the player has rolled a 1
* Parameters: int die1value, int die2value - values of the two dice rolled
* Return: true if a 1 has been rolled, false if not
*/
bool isTurnScoreLost ( int die1value, int die2value )
{
	if (die1value == 1 || die2value == 1)
	{
		return true;
	}
	return false;
}

/*
* isGameScoreLost: evaluates if the player has rolled double 1
* Parameters: int die1value, int die2value - values of the two dice rolled
* Return: true if double 1 has been rolled, false if not
*/
bool isGameScoreLost ( int die1value, int die2value )
{
	if (die1value == 1 && die2value == 1)
	{
		return true;
	}
	return false;
}

/*
 * checkWin: checks to see who the winner is
 * Parameters: int player,  the players score to see if they won, if not it is the computer 
		that won
 * Return: none
*/
void checkWin (int player)
{
	if (player >= WIN_VALUE)
	{
		cout << "\n YOU WIN!! \n";
	}
	else
	{
		cout << "\n YOU LOSE!! \n";
	}
}