package cscie160.hw6;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Server class for the multithreaded ATM (Homework 6).  Based on the provided
 * HW4 Server class, and enhanced for multithreading.
 * 
 * @author	Charlie Sawyer
 * @author	Larry Tambascio
 * @version	1.1
 */
public class Server 
{
	/**
	 * Thread pool
	 */
	private Vector<Thread>	threads;
	
	/**
	 * Requests to be processed
	 */
	private Vector<ATMRunnable>	requests;
	
    private ServerSocket serverSocket;
    
    private ATM atmImplementation;
    
    private BufferedReader bufferedReader;
    
    /**
     * Constructor that takes a port number for initializing the ServerSocket.
     * 
     * @param	port	Port number to listen on
     * @throws	IOException	Potentially thrown by creating the ServerSocket. 
     */
    public Server(int port) throws IOException 
	{
		serverSocket = new ServerSocket(port);
		
		atmImplementation = new ATMImplementation();
		
		requests = new Vector<ATMRunnable>(5);
		
		threads = new Vector<Thread>(5);
		for (int i = 0; i < 5; i++)
		{
			Thread thread = new Thread(new ATMThread(requests));
			threads.add(thread);
			thread.start();
		}
    }
	
    /** 
     * serviceClient accepts a client connection and reads lines from the socket.
     * Each line is handed to executeCommand for parsing and execution.
     * 
     * @throws	IOException	From socket client connection.
     */
    public void serviceClient() throws java.io.IOException 
	{
		System.out.println("Accepting clients now");
		while (true)
		{
			Socket clientConnection = serverSocket.accept();
			
			// Arrange to read input from the Socket	
			InputStream inputStream = clientConnection.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			// Arrange to write result across Socket back to client
			OutputStream outputStream = clientConnection.getOutputStream();
	        PrintStream printStream = new PrintStream(outputStream);
			
			System.out.println("Client acquired on port #" + serverSocket.getLocalPort() + ", reading from socket");
		
			try
			{
					String commandLine;
					while ((commandLine = bufferedReader.readLine()) != null) 
					{
						ATMRunnable command = new ATMRunnable(commandLine, atmImplementation, printStream);
						synchronized (requests)
						{
							requests.add(command);
							requests.notify();
						}
					}
			}
			catch (SocketException sException)
			{
				// client has stopped sending commands.  Exit gracefully.
				System.out.println("done");
			}
		}
	}
	
    /**
     * Main method for starting up the server process.
     * 
     * @param argv	Command line parameters - port number
     */
	public static void main(String argv[]) 
	{
		int port = 1099;
		if(argv.length > 0) 
		{
			try 
			{
				port = Integer.parseInt(argv[0]);
			} 
			catch (Exception e) 
			{ 
				e.printStackTrace(); 
			}
		}
		try 
		{
			Server server = new Server(port);
			server.serviceClient();
			System.out.println("Client serviced");
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}
