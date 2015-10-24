/* Name: MP1
 * Date: 10-3-13
 * Author: Jason Duffey
 *
 * Receives the number of donuts from the user, determines the cost of the purchase
 * including tax, receives how much payment was received and calculates the change,
 * and coinage to be returned to the customer.
*/

#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
	const int CENTS_PER_DOLLAR = 100;
	const int CENTS_PER_QUARTER = 25;
	const int CENTS_PER_DIME = 10;
	const int CENTS_PER_NICKEL = 5;
	const int DOZEN_FILLED = 825; //cost of a dozen filled donuts in cents
	const int SINGLE_FILLED = 75; //cost of a single filled donut in cents
	const int DOZEN_REGULAR = 525; //cost of a dozen regular donuts in cents
	const int SINGLE_REGULAR = 50; //cost of a single regular donut in cents
	const int TAX_RATE = 4 ;//tax rate in cents per dollar
	 

	//asks the user how many donuts have been ordered
	int reg_donuts;
	int filled_donuts;
	cout << "Number of regular donuts ordered: ";
	cin >> reg_donuts;
	cout << "Number of filled donuts ordered: ";
	cin >> filled_donuts;

	//calculates the cost of regular donuts ordered
	int subtotal = 0;
	subtotal += ((reg_donuts / 12) * DOZEN_REGULAR);
	reg_donuts %= 12;
	subtotal += reg_donuts * SINGLE_REGULAR;

	//adds the cost of filled donuts ordered
	subtotal += ((filled_donuts / 12) * DOZEN_FILLED);
	filled_donuts %= 12;
	subtotal += filled_donuts * SINGLE_FILLED;

	//calculates the tax on the order
	int tax = TAX_RATE * subtotal / 100;

	//calculate the total cost of the order and outputs the total
	int totalCost;
	totalCost = tax + subtotal;
	cout << "Customer owes $" << totalCost / CENTS_PER_DOLLAR << "." << setw(2) << setfill ('0')
		<<  totalCost % CENTS_PER_DOLLAR << endl;

	//asks the user for how much is paid and calculates it to cents
	int dollars; //will store the number of dollars that the customer pays
	char period; //will take the decimal for the price inputed as dollars and cents
	int cents; //will store the number of cents that the customer 
	int amountPaid;
	cout << "Customer pays $";
	cin >> dollars >> period >> cents;
	amountPaid = (dollars * CENTS_PER_DOLLAR) + cents;

	//calculates the change owned and what coinage to tend back
	int changeOwed = amountPaid - totalCost;
	int dollarChange; // dollars to be given as change
	int quarters;     // quarters to be given as change
	int dimes;        // dimes to be given as change
	int nickels;      // nickels to be given as change
	int pennies;      // pennies to be given as change
	if (0 == changeOwed)
	{
		cout << "Exact payment received - no change owed." << endl;
	}
	else
	{
		cout << "Change owed is $" << changeOwed / CENTS_PER_DOLLAR
			<< "." << setw(2) << setfill('0') << changeOwed % CENTS_PER_DOLLAR
			<< " - ";
		dollarChange = changeOwed / CENTS_PER_DOLLAR;
		changeOwed -= dollarChange * CENTS_PER_DOLLAR;
		quarters = changeOwed / CENTS_PER_QUARTER;
		changeOwed -= quarters * CENTS_PER_QUARTER;
		dimes = changeOwed / CENTS_PER_DIME;
		changeOwed -= dimes * CENTS_PER_DIME;
		nickels = changeOwed / CENTS_PER_NICKEL;
		changeOwed -= nickels * CENTS_PER_NICKEL;
		pennies = changeOwed;

		//outputs the dollars to be tended
		if (dollarChange > 1)
		{
			cout << dollarChange << " dollars";
			if (quarters || dimes || nickels || pennies)
			{
				cout << ", ";
			}
		}
		else if (dollarChange == 1)
		{
			cout << dollarChange << " dollar";
			if (quarters || dimes || nickels || pennies)
			{
				cout << ", ";
			}
		}
		
		//outputs the quarters to be tended
		if (quarters > 1)
		{
			cout << quarters << " quarters";
			if (dimes || nickels || pennies)
			{
				cout << ", ";
			}
		}
		else if (quarters == 1)
		{
			cout << quarters << " quarter";
			if (dimes || nickels || pennies)
			{
				cout << ", ";
			}
		}

		//outputs the dimes to be tended
		if (dimes > 1)
		{
			cout << dimes << " dimes";
			if (nickels || pennies)
			{
				cout << ", ";
			}
		}
		else if (dimes == 1)
		{
			cout << dimes << " dime";
			if (nickels || pennies)
			{
				cout << ", ";
			}
		}

		//outputs the number of nickels to be tended
		if (nickels > 1)
		{
			cout << nickels << " nickels";
			if (pennies)
			{
				cout << ", ";
			}
		}
		else if (nickels == 1)
		{
			cout << nickels << " nickel";
			if (pennies)
			{
				cout << ", ";
			}
		}
		
		//outputs the number of pennies to be tended
		if (pennies > 1)
		{
			cout << pennies << " pennies";
		}
		else if (pennies == 1)
		{
			cout << pennies << " penny";
		}

		cout << "." << endl;
	}
	return 0;
}