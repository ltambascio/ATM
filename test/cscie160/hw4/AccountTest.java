package cscie160.hw4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the Account class.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class AccountTest
{
	private Account account;

	@Before
	public void setUp() throws Exception
	{
		account = new Account();
	}

	@Test
	public void testGetBalance()
	{
		float zero = 0;
		assertEquals("initialized correctly", zero, account.getBalance(), 0.0);
		account.setBalance(2500);
		assertEquals("balance udpated", 2500, account.getBalance(), 0.0);
	}

	@Test
	public void testSetBalance()
	{
		account.setBalance(10000);
		assertEquals("balance udpated", 10000, account.getBalance(), 0.0);
	}

}
