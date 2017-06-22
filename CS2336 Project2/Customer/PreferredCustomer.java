/* 
 * Victor Mao (vtm160030)
 * CS 2336.003 Project 2
 * Disneyland Dining Rewards
 * PreferredCustomer derived class
 */

package Customer;

public class PreferredCustomer extends Customer
{
	// Private Variables
	private int discount;
	
	// Overloaded Constructor
	public PreferredCustomer(int i, String fn, String ln, double as, int dp)
	{
		super(i, fn, ln, as);
		discount = dp;
	}
	
	// Getter
	public int getDiscountPercentage() { return discount; }
	
	// Setter
	public void setDiscountPercentage(int dp) { discount = dp; }
}