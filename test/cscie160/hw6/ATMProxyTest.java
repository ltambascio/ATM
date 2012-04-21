package cscie160.hw4;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test case will validate the ATMProxy class.  It requires that the server
 * process is up an running on localhost on port 7777.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class ATMProxyTest
{
	private static ATMProxy	atmProxy;

	/**
	 * Instantiate an instance to test.
	 * 
	 * @throws	Exception	Covers exceptions thrown by the constructor
	 */
	@BeforeClass
	public static void setUp() throws Exception
	{
		atmProxy = new ATMProxy("localhost", 7777);
	}
	
	/**
	 * Test the deposit method
	 * 
	 * @throws ATMException Thrown by deposit method.
	 */
	@Test
	public void testDeposit() throws ATMException
	{
		atmProxy.deposit(1500);
		assertEquals("Deposited $1,500", 1500, atmProxy.getBalance(), 0);
	}

	/**
	 * Test the withdraw method
	 * 
	 * @throws ATMException	Thrown by withdraw method.
	 */
	@Test
	public void testWithdraw() throws ATMException
	{
		atmProxy.withdraw(2500);
		assertEquals("Withdrew $2,500", -1000, atmProxy.getBalance(), 0);
	}

	/**
	 * Test the get balance method over a couple of transactions (1 deposit and
	 * 1 withdrawal).
	 * @throws ATMException Thrown by ATMProxy methods.
	 */
	@Test
	public void testGetBalance() throws ATMException
	{
		assertEquals("current balance of -$1,000", -1000, atmProxy.getBalance(), 0);
		
		atmProxy.deposit(5000);
		assertEquals("deposited $5,000", 4000, atmProxy.getBalance(), 0);
		
		atmProxy.withdraw(1987);
		assertEquals("withdrew $1,987", 2013, atmProxy.getBalance(), 0);
	}

}
