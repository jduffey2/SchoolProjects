/* Name: cipher
 * Date: 12/12/13
 * Author: Jason Duffey
 *
 * Improves upon part 1 of MP4, including spaces and lowercase letters into
 * the cipher program, it reads in plain text or cipher text from a file, and 
 * converts to the other based on the user's command line prompt using a 
 * Vigenere cipher.
 * NOTE: The table uses the characters in ASCII value order when enciphering/deciphering
*/

#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <cstdlib>

using namespace std;

void pressEnterToContinue (void);
char encipher (char key, char plain);
char decipher (char key, char cipher);

/* * * *MAIN* * * */
int main (int argc, char *argv[] )
{
	ifstream inputStream; //reads in the text for conversion 
	ofstream outputStream; //text to be written into a text document
	string key; //key word to be used in the Vigenere cipher

	//open the input file and verify for reading
	inputStream.open( argv[2] );
	if ( inputStream.fail () )
	{
		cout << "Sorry - cannot open input file. \n";
		pressEnterToContinue();
		exit (1 );
	}

	//open the output file and verify for reading
	outputStream.open (argv[3] );
	if (outputStream.fail () )
	{
		cout << "Sorry - cannot open output file. \n";
		pressEnterToContinue();
		exit (1);
	}

	//sets the key word for the encryption/decryption process
	cout << "What key should be used: ";
	getline( cin, key);

	char ch; //variable to hold the character of the message that is being read inl
	int keyCounter = 0; //counts what letter of the message is beign read in
	int keyLength = key.length();

	//checks if the flag set is for encryption or ecryption and runs the appropriate process
	if (argv[1][1] == 'e')
	{
		//run the ENCRYPTION process
		while (inputStream.get(ch) )
		{
			//cycles through the characters of the key as it writes to the file
			outputStream.put(encipher (key[keyCounter % keyLength], ch) );
			keyCounter++;
		}
		pressEnterToContinue();
	}
	else if (argv[1][1] == 'd')
	{
		//run the DECRYPTION process
		while (inputStream.get(ch) )
		{
			//cycles through the characters of the key as it writes to the file
			outputStream.put(decipher (key[keyCounter % keyLength], ch) );
			keyCounter++;
		}
		pressEnterToContinue();
	}
	else
	{
		//in case the user does not use d or e to signal the process to be used
		cout << "Sorry - I do not know what process you want me to run.";
		pressEnterToContinue();
		exit (1);
	}

	return 0;
}
/* * * * FUNCTION DEFINITIONS * * * */

/* pressEnterToContinue: implements a system pause when necessary
 * Parameters: none
 * Return: none
*/
void pressEnterToContinue (void)
{
	cout << "\n Press 'Enter' to continue... ";
	cin.ignore( 1024, '\n' );
	cin.clear();
	return;
}

/* encipher: enciphers a character of plain text into an encrypted letter
* Parameters: key - a character that is shifted to the place of ' ' (SPACE)
* plain - a character that is to be encoded using the shifted alphebet set by the key
* Return: the encrypted character
*/
char encipher (char key, char plain )
{
	char encLetter; //holder for the character as it gets encrypted
	char rollover; //used in case the character is shifted around to the front
				   //of the Vigenere table

	//makes sure the plaintext and key are appropriate characters
	//returns the plaintext if not
	if (plain > '~' || plain < ' ')
		return plain;
	if (key > '~' || key < ' ') 
		return plain;

	encLetter = plain + (key - ' '); // adds the shift to the plaintext letter

	//returns the encrypted character if the shift does not need to be rolled over
	//to the front of the table
	if (encLetter <= '~' && encLetter >= ' ')
	{
		return encLetter;
	}
	
	//handles the process of rolling over the characters to the front of table
	//figures out how far the character should be from the front of the table
	//takes how much the word should be shifted minus the distance the plaintext
	//is from the end of the table, adds it to the front of the table of characters
	rollover = (key - ' ') - ('~' - plain); 
	encLetter = 31 + rollover; //ASCII value 31 is used to appropriately assign characters
	return encLetter;
}

/* decipher: deciphers ciphertext into plaintext using the given key letter
* Parameters: key - character that is shifted to ' ' for the decryption
*  cipher - character of the cipher text that is to be decoded
* Return: the plain text character after it has been deciphered
*/
char decipher ( char key, char cipher )
{
	char plain; // store the character as it gets deciphered back to plaintext

	//makes sure ciphertext and key are appropriate characters
	//returns the ciphertext character if not
	if (cipher > '~' || cipher < ' ') 
		return cipher;
	if (key > '~' || key < ' ') 
		return cipher;

	plain = cipher - (key - ' '); //subtracts the shift from the encrypted letter

	//rolls charater value less than ' ' back to the end of the table '~'
	// ASCII value 127 is used to correctly roll letters over to the end of the alphabet
	if (plain < ' ')
		plain = 127 - ( ' ' - plain);

	return plain;
}