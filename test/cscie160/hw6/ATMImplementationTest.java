package cscie160.hw6;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test will validate the ATMImplmentation class that it deposits, 
 * withdraws, and returns the deposit correctly.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class ATMImplementationTest
{
	private ATMImplementation atmImpl;

	/**
	 * Set up the test harness by simply instantiating an ATMImplementation 
	 * instance.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		atmImpl = new ATMImplementation();
	}

	/**
	 * Test the deposit and get balance functions.
	 * 
	 * @throws ATMException
	 */
	@Test
	public void testDeposit() throws ATMException
	{
		atmImpl.deposit(375);
		assertEquals("desposited $375", 375, atmImpl.getBalance(), 0);
		atmImpl.deposit(125);
		assertEquals("deposited another $125", 500, atmImpl.getBalance(), 0);
	}

	/**
	 * Test the withdraw and get balance functions.
	 * 
	 * @throws ATMException
	 */
	@Test
	public void testWithdraw() throws ATMException
	{
		atmImpl.deposit(600);
		assertEquals("first deposit $600", 600, atmImpl.getBalance(), 0);
		atmImpl.withdraw(275);
		assertEquals("now withdraw 275", 325, atmImpl.getBalance(), 0);
		atmImpl.withdraw(225);
		assertEquals("and another 225", 100, atmImpl.getBalance(), 0);
	}

}
