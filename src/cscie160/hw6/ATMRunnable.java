package cscie160.hw6;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * Executes client requests, this class is the "work-order" request object.
 * 
 * @author	Larry Tambascio
 * @version	1.0
 */
public class ATMRunnable implements Runnable
{
    Logger log = Logger.getLogger(ATMRunnable.class);
    
    /**
     * Remote reference to ATM
     */
	private ATM	atmImplementation;
    
	/**
	 * Command line to execute
	 */
    private String commandLine;
    
    /**
     * Reference to client to write the response to
     */
    private PrintStream output;
    
    /**
     * Constructor.
     * 
     * @param commandLine	Command line to execute
     * @param atmRemote		Remote reference to ATM
     * @param output		Output stream to write response back to
     */
	public ATMRunnable(String commandLine, ATM atmRemote, PrintStream output)
	{
		this.commandLine = commandLine;
		this.atmImplementation = atmRemote;
		this.output = output;
	}

	/**
	 * Mostly contains the old executeCommand method from the HW4 Server class.
	 */
	@Override
	public void run()
	{
		// Break out the command line into String[]
		StringTokenizer tokenizer = new StringTokenizer(commandLine);
		log.debug("processing: " + commandLine);
		String commandAndParam[] = new String[tokenizer.countTokens()];
		int index = 0;

		while (tokenizer.hasMoreTokens()) 
		{
			commandAndParam[index++] = tokenizer.nextToken();
		}
		
		String command = commandAndParam[0];
		try {
			// Dispatch BALANCE request without further ado.
			if (command.equalsIgnoreCase(Commands.BALANCE.toString())) 
			{
				output.println(atmImplementation.getBalance());
				return;
			}
			// Must have 2nd arg for amount when processing DEPOSIT/WITHDRAW commands
			if (commandAndParam.length < 2) 
			{
				throw new ATMException("Missing amount for command \"" + command + "\"");
			}
			
			try {
				float amount = Float.parseFloat(commandAndParam[1]);
				if (command.equalsIgnoreCase(Commands.DEPOSIT.toString())) 
				{
					atmImplementation.deposit(amount);	      
				}
				else if (command.equalsIgnoreCase(Commands.WITHDRAW.toString())) 
				{
					atmImplementation.withdraw(amount);
				}
				else 
				{
					throw new ATMException("Unrecognized command: " + command);
				}
			} 
			catch (NumberFormatException nfe) 
			{
				throw new ATMException("Unable to make float from input: " + commandAndParam[1]);
			}
		}
		catch (ATMException atme)
		{
			log.error("ATM Exception: " + atme.getMessage());
		}
	}

}
