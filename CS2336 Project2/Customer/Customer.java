/* 
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 2
 * Disneyland Dining Rewards
 * Customer base class
 */

package Customer;

public class Customer
{
	// Private Variables
	private int id;
	private String firstName;
	private String lastName;
	private double amountSpent;
	
	// Overloaded Constructor
	public Customer(int i, String fn, String ln, double as)
	{
		id = i;
		firstName = fn;
		lastName = ln;
		amountSpent = as;
	}
	
	// Getters
	public int getId() { return id; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public double getAmountSpent() { return amountSpent; }
	
	// Setters
	public void setId(int i) { id = i; }
	public void setFirstName(String fn) { firstName = fn; }
	public void setLastName(String ln) { lastName = ln; }
	public void setAmountSpent(double as) { amountSpent = as; }
}