package unit3_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

	private ServerMain serverMain;
	private Socket regularSocket;
	
	public ServerThread(ServerMain serverMain, Socket regularSocket) {
		super();
		this.serverMain = serverMain;
		this.regularSocket = regularSocket;
	}
	
	@Override
	public void run() {
		try {
			int clientCount = serverMain.getClientCount();
			System.out.println("Client " + clientCount + " with IP = " 
								+ regularSocket.getInetAddress()
								+ " has connected");
			
			// Step 2: Create I/O Buffers
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(regularSocket.getInputStream()));
			PrintWriter toClient = new PrintWriter(new OutputStreamWriter(regularSocket.getOutputStream()), true);
			
			// Step 3: Create communication
			toClient.println("Welcom, you are client number " + clientCount);
			toClient.println("What is your name?");
			System.out.println("Client number " + clientCount
								+ " says: " + fromClient.readLine());
			
			// Step 4: Close regular socket
			regularSocket.close();
			System.out.println("Client number " + clientCount + " has disconnected");
			System.out.println("Socket is closes");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
