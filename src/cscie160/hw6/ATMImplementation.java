package cscie160.hw6;

/**
 * Server side implementation of the ATM.  This class will receive requests and
 * update the account as appropriate.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class ATMImplementation implements ATM
{
	private Account account;
	
	/**
	 * No arg constructor that will initiate the one account this ATM manages.
	 */
	public ATMImplementation()
	{
		account = new Account();
	}

	/**
	 * This method will add the deposited amount to the account's balance.
	 * 
	 * @param	amount	Amount to deposit
	 * @throws	ATMException	Wrapper for some networking error
	 */
	@Override
	public void deposit(float amount) throws ATMException
	{
		float currBalance;
		
		currBalance = account.getBalance();
		account.setBalance(currBalance + amount);
	}

	/**
	 * This method will deduct the withdrawn amount from the account's balance.
	 * 
	 * @param	amount	Amount to withdraw
	 * @throws	ATMException	Wrapper for networking error
	 */
	@Override
	public void withdraw(float amount) throws ATMException
	{
		float currBalance;
		
		currBalance = account.getBalance();
		account.setBalance(currBalance - amount);
	}

	/**
	 * @return	Current balance of the account.
	 * @throws	ATMException	Wrapper for networking error
	 */
	@Override
	public Float getBalance() throws ATMException
	{
		return account.getBalance();
	}

}
