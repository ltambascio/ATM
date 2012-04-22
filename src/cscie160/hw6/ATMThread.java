package cscie160.hw6;

import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * @author	Larry Tambascio
 * @version	1.0
 */
public class ATMThread implements Runnable
{
	private static Logger log = Logger.getLogger(ATMThread.class);
	
	private Vector<ATMRunnable>	requests;
	
	public ATMThread (Vector<ATMRunnable> requests)
	{
		this.requests = requests;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				synchronized (requests)
				{
					if (requests.isEmpty())
					{
						log.debug("starting to wait on requests");
						requests.wait();
					}
				}
			}
			catch (InterruptedException ie)
			{
				log.debug("Request interruption");
			}
			
			// there should be a request waiting for us now.
			synchronized (requests)
			{
				ATMRunnable request;
				
				request = requests.get(0);
				requests.remove(0);
				
				Thread reqThread = new Thread(request);
				reqThread.start();
			}
		}	// infinite loop

	}

}
