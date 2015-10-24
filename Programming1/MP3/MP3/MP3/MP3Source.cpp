/* Name: MP3
* Date: 11 - 7 - 13
* Author: Jason Duffey
*
* Plays a series of two-player tic - tac - toe. Alternates who goes first 
* and what symbol each player plays with, keeps track of each players record
*/

#include <iostream>
#include <string>

using namespace std;

const int BOARD_SIZE = 9; //size of the tic tac toe board
const int ASCII_1 = 49; //ASCII value for the character '1';
const char PLAYER1_MARK = 'X'; //the mark player 1 will use
const char PLAYER2_MARK = 'O'; //the mark player 2 will use

void displayBoard (char board[], const int SIZE);
int getPlayerInput (string playerName);
bool isLegalMove (int location, char board[], int boardSize);
void placeMarkOnBoard (char playerMark, int location, char board[], int boardSize );
void clearBoard (char board[], int boardSize);
bool hasThreeInRow (char playerMark, char board[], int boardSize);
bool newGame ();
void playerTurn ( string player, char playerMark, char board[], int size, int& winCount,
				 int& turnCounter, bool& win);

/* * * * MAIN * * * */
int main () 
{
	char board[BOARD_SIZE] = {'1', '2', '3', '4', '5', '6', '7', '8', '9' };
	string player1; // name of player 1
	string player2; // name of player 2
	int wins_1 = 0; // number of wins player 1 has
	int wins_2 = 0; // number of wins player 2 has
	int draw = 0; // number of ties the players have had
	int turnCounter = 0; //used to keep track of how many squares have been played
	bool win = false; //used to break out of the loops if someone wins

	//Welcome message
	cout << "Welcome to 2-player tic-tac-toe!\n\n";
	
	//get the player's names
	cout << "Player 1, what would you like to be called: ";
	getline (cin, player1);
	cout << "Player 2, what would you like to be called: ";
	getline (cin, player2);
	cout << endl;

	do
	{
		//player 1 goes first
		win = false;
		clearBoard (board, BOARD_SIZE);
		displayBoard(board, BOARD_SIZE);
		turnCounter = 0;

		while (turnCounter < BOARD_SIZE && !win)
		{
			//player1 turn
			playerTurn (player1, PLAYER1_MARK, board, BOARD_SIZE, wins_1, turnCounter, win);

			//placed in between two players since player 1 would always be the last
			// person to be able to play a square
			// checks for the tie
			if (turnCounter == BOARD_SIZE)
			{
				draw++;
				cout << "It's a draw! No one wins.\n\n";
				break;
			} 
			if (win)
				break;

			//player 2's turn
			playerTurn(player2, PLAYER2_MARK, board, BOARD_SIZE, wins_2, turnCounter, win);
		}
		
		//Display each person's wins and the number of draws
		cout << player1 << ": " << wins_1 << endl << player2 << ": " 
			<< wins_2 << endl << "Draws: " << draw << endl << endl;

		if (!newGame() ) //exits if they don't want to play again
			break;

		//player 2 plays first
		win = false;
		clearBoard (board, BOARD_SIZE);
		displayBoard(board, BOARD_SIZE);
		turnCounter = 0;

		while (turnCounter < BOARD_SIZE && !win)
		{
			//player2 turn
			playerTurn (player2, PLAYER2_MARK, board, BOARD_SIZE, wins_2, turnCounter, win);

			//placed in between two players since player 2 would always be the last
			// person to be able to play a square
			//checks for the tie
			if (turnCounter == BOARD_SIZE)
			{
				draw++;
				cout << "It's a draw! No one wins.\n\n";
				break;
			} 
			if (win)
				break;

			//player 1's turn
			playerTurn(player1, PLAYER1_MARK, board, BOARD_SIZE, wins_1, turnCounter, win);
		}
		
		//Display each person's wins and the number of ties
		cout << player1 << ": " << wins_1 << endl << player2 << ": " 
			<< wins_2 << endl << "Draws: " << draw << endl << endl;

	}
	while (newGame() );
	
	//When the players no longer wish to play
	cout << "Thanks for playing!\n";
	return 0;
}


/* * * * FUNCTION DEFINITIONS * * * */


/* displayBoard: prints out the current board for the game
* Parameters: char board[] - board that is being printed, 
*    const int SIZE - size of board
* Return: nothing
*/
void displayBoard (char board[], int boardSize)
{
	int squaresPerRow = 3; //number of squares per row,
	//used as offset to align board with the number in each square

	cout << endl;
	for (int row = 2; row >= 0; row--)
	{
		cout << " ";
		for (int column = 0; column <= 2; column++)
		{
			cout << " " << board[row * squaresPerRow + column] << " ";
			if (column < 2) //places the column divider for the first two columns
				cout << "|";
		}
		if (row > 0)
			cout << "\n ---+---+--- \n";
	}
	cout << endl << endl;
	return;
}

/* getPlayerInput: asks user where they want to play their mark, checks for correct input
* Parameters: string playerName - the player who's turn it is
* Return: the value of the square where the player wishes to go (1-9)
*/
int getPlayerInput (string playerName)
{
	int playerChoice;
	while (true)
	{
		cout << playerName << ", please choose your square: ";
		cin >> playerChoice;
		cout << endl;

		//checks for correct input
		if (cin.fail() )
		{
			cout << "That is not a valid input! Pick a square (1-9) "
				<< "not already taken." << endl;
			cin.clear();
			cin.ignore ( 1024, '\n'); //flushes the input buffer
			continue;
		}

		//checks to see if number is in range
		if (playerChoice > BOARD_SIZE || playerChoice <= 0)
		{
			cout << "That isn't a square on the board! Pick a square (1-9) "
				<< "not already taken." << endl;
			continue;
		}

		//if input is correct and valid
		break;
	}
	return playerChoice;
}

/* isLegalMove: Determines if a square has already been played or not
* Parameters: int location - square to be checked that the player picked, 
char board[] - the current game board, int size - size of game board
* Return: true if square is unused, false if alreday used
*/
bool isLegalMove (int location, char board[], int size)
{
	//if the position on the board (which is indexed one less than the user input)
	// does nto have an 'X' or an 'O' in it, then it is a legal move
	if (board[ location - 1] != 'X' && board[ location - 1] != 'O' )
	{
		return true;
	}
	return false;
}

/* placeMarkOnBoard: places the appropriate mark on the board that the user picks
* Parameters: playerMark - the players mark to be placed on board, 
*		location - the square the player picks for his turn, board[] - the game board array
*		boardSize - the size of the game board array
* Return: none
*/
void placeMarkOnBoard (char playerMark, int location, char board[], int boardSize )
{
	board[ location - 1] = playerMark;
}

/* clearBoard: restores the board array to the original state
* Parameters: board[] - the game board as an array, boardSize - size of the gameboard
* Return: none
*/
void clearBoard (char board[], int boardSize)
{
	int letter = ASCII_1;

	//sets each value in the board array to the character equivalent of the number
	for (int i = 0; i < boardSize; i++)
	{
		board[i] =  letter;
		letter++;
	}
}

/* hasThreeInRow: determines if the players has placed three of his marks in a row
* Parameters: playerMark - the mark that the player is using on the board
* board[] - the array that is storing the board, boardSize - size of the board
* Return: true if they have three in a row, false if not
*/
bool hasThreeInRow (char playerMark, char board[], int boardSize)
{
	//checks if the player has three in one column, any column
	for (int col = 0; col <= 2; col++)
	{
		if (board[col] == playerMark && board[col + 3] == playerMark && 
			board[col + 6] == playerMark)
		{
			return true;
		}
	}

	//checks if the player has three in one row, any row
	for (int row = 0; row <= 6; row += 3)
	{
		if (board[row] == playerMark && board[row + 1] == playerMark && 
			board[row + 2] == playerMark)
		{
			return true;
		}
	}

	//checks if the player has three in a row on the diagonals
	//just hard checks the square array values for three in a row
	if (board[0] == playerMark && board[4] == playerMark && 
		board[8] == playerMark)
	{
		return true;
	}
	if (board[2] == playerMark && board[4] == playerMark && 
		board[6] == playerMark)
	{
		return true;
	}

	//if here they do not have a three in a row
	return false;
}

/* newGame: asks if they want to play another game
 * Parameters:  none
 * Return: true if they want to play again, false if  not
*/
bool newGame ()
{
	char response;

	while (true)
	{
		cout << "Would you like to play again? (Y for yes, N for no): ";
		cin >> response;
		if (cin.fail() )
		{
			cout << "I do not understand what you want.\n";
			cin.clear();
			cin.ignore ( 1024, '\n'); //flushes the input buffer
			continue;
		}
		if (response == 'Y' || response == 'y')
			return true;
		else
			return false;
	}
}

/*playerTurn: executes everything necessary for one players turn
* Parameters: player - whose turn it is, playerMark, board, size - board size, 
	winCount - player's whose turn it is wins, turnCounter - how many turns have 
	been in the game, bool win - if a player has won the game)
* Return: none
*/
void playerTurn ( string player, char playerMark, char board[], int size, int& winCount,
				 int& turnCounter, bool& win)
{
	int playerChoice; 	
	
	//gets player input and marks board
	while (true)
	{
		playerChoice = getPlayerInput ( player ); 
		
		//places the player mark on the board, and shows the updated board
		if (isLegalMove (playerChoice, board, size) )
		{
			turnCounter++;
			placeMarkOnBoard (playerMark, playerChoice, board, size);
			displayBoard (board, size);
			break;
		}
		else
		{
			cout << "That square has already been picked!\n";
		}
	}
	
	//checks if they have won the game and increases their win count if so
	if ( hasThreeInRow (playerMark, board, size) )
	{
		cout << player << " wins!\n\n";
		winCount++;
		win = true;
		return;
	}
}

