package frogs;



import java.io.IOException;

import java.net.ServerSocket;

/**
 * @author  Ferry To
 */
public class Server 
{
	static final ConnectionManager connManager = ConnectionManager.getManager();
	static Server server;
	
	ServerSocket ss;
	int port;
	
	protected Server()
	{
		port = ServerConfiguration.getNumericParameter("ServerPort");
	}
	
	public static Server getInstance()
	{
		if(server==null) 
			server = new Server();
		return server;
	}
	
	public void startServer() 
	{
		boolean keepRunning = true;
		
		Logger.printMsg(this,"Server starting...");
		try 
		{
			ss = new ServerSocket(port);
		} 
		catch (IOException e) 
		{
			Logger.printMsg(this,"Could not listen on port: "+port);
			System.exit(1);
		}

		Logger.printMsg(this,"FROGS started.");
		while(keepRunning) 
		{
			try 
			{
				connManager.bindConnection(ss.accept());
			}
			catch (IOException e) 
			{
				//Just simply break the loop in release version. 
				//e.printStackTrace();
				//Messager.printMsg(this,"Accept failed:3333");
				keepRunning = false;
			}
		}
	}
	
	public void closeServer()
	{
		Logger.printMsg(this,"FROGS closing...");
		try
		{
			if(ss != null) ss.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		Logger.printMsg(this,"FROGS closed.");
	}
}