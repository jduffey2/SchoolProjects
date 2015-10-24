/* Name: MP4
* Date: 12/19/13
* Author: Jason Duffey
*
* Uses the Vigenere cipher to encode and decode a message given by the user
* using a key word also given by the user
*/

#include <iostream>
#include <cstring>
#include <string>
#include <cstdlib>

using namespace std;

char encipher ( char key, char plain );
char decipher ( char key, char cipher);

/* * * * MAIN * * * */
int main()
{
	string key;
	string inmessage;

	while (true)
	{	
		char runAgain;
		
		cout << "Would you like to run another cipher? (Y for yes, N for no): ";
		cin >> runAgain;

		if(runAgain != 'y' && runAgain != 'Y')
			break;
		
		//takes in the key word to be used
		cout << "What key word should be used to encrypt the message: ";
		cin >> key;

		//takes in the message to be encrypted
		cout << "What message would like to encrypt: ";
		cin >> inmessage;

		int msg_length = inmessage.length(); //length of the message to encrypt
		int key_length = key.length(); // length of the keyphrase used

		//character arrays for using in the encryption/decryption process
		char* encMessage = new char[msg_length]; // will hold the end encrypted message
		char* decMessage = new char[msg_length]; // will hold the end decrypted message
		
		//encrypts the given message using the key, then decrypts the resultant
		//ciphertext back to plaintext
		for (int i = 0; i < msg_length; i++)
		{
			encMessage[i] = encipher ( key[i % key_length], inmessage[i]);
			decMessage[i] = decipher ( key[i % key_length], encMessage[i]);
		}

		//outputs the encrypted and decrypted messages
		cout << endl;
		for (int i = 0; i < msg_length; i++)
		{
			cout << encMessage[i];
		}
		cout << endl;

		for (int d = 0; d < msg_length; d++)
		{
			cout << decMessage[d];
		}
		cout << endl;

		//deletes the allocated memory
		delete [] encMessage;
		delete [] decMessage;
	}
	return 0;
}

/* * * * FUNCTION DEFINITIONS * * * */

/* encipher: enciphers a character of plain text into an encrypted letter
* Parameters: key - a character that is shifted to the place of 'A' 
* plain - a character that is to be encoded using the shifted alphebet set by the key
* Return: the encrypted character
*/
char encipher (char key, char plain )
{
	char encLetter; //holder for the letter as it gets encrypted

	if (plain > 'Z' || plain < 'A') //makes sure plaintext is a capital letter, returns it if not
		return plain;
	if (key > 'Z' || key < 'A') // makes sure key is a capital letter, returns it if not
		return plain;

	encLetter = plain + (key - 'A'); // adds the shift to the plaintext letter

	//checks if the shift takes it out of the range of the capital letters
	//64 is used as the ASCII value to roll over to the correct letter
	if (encLetter > 'Z')
	{
		encLetter = 64 + (encLetter - 'Z'); //rolls over out of range char back to 'A'
	}

	return encLetter;
}

/* decipher: deciphers ciphertext into plaintext using the given key letter
* Parameters: key - character that is shifted to 'A' for the decryption
*  cipher - character of the cipher text that is to be decoded
* Return: the plain text character after it has been deciphered
*/
char decipher ( char key, char cipher )
{
	char plain; // store the character as it gets deciphered back to plaintext

	if (cipher > 'Z' || cipher < 'A') //makes sure plaintext is a capital letter, returns it if not
		return cipher;
	if (key > 'Z' || key < 'A') // makes sure key is a capital letter, returns it if not
		return cipher;

	plain = cipher - (key - 'A'); //subtracts the shift from the encrypted letter

	//rolls letter less than 'A' back to the end of the alphabet 'Z'
	// ASCII value 91 is used to correctly roll letters over to the end of the alphabet
	if (plain < 'A')
		plain = 91 - ( 'A' - plain);

	return plain;
}