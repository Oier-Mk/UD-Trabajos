package es.deusto.ingenieria.sd.strava.server.gateway.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;

public class FacebookServiceGateway implements IServiceGateway{

	private static IServiceGateway instance;
	private Socket tcpSocket;

	private FacebookServiceGateway(String serverIP, int serverPort, String serverName) {
		try {		
			tcpSocket = new Socket(serverIP,serverPort);
		} catch (Exception ex) {
			System.err.println("# Error locating remote fa�ade: " + ex);
		}
	}
	
	@Override
	public boolean login(String mail, String password) throws IOException {
		boolean data = false;
		DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
		DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream());

		String message = mail+"#"+password; 
		//Send request (a String) to the server
		out.writeUTF(message);
		System.out.println(" - TCPSocketClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + message + "'");

		//Read response (a String) from the server
			try{
				data = in.readBoolean();
			}catch (Exception e) {
				e.printStackTrace();
			}

		System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> " + data);
		System.out.println("existe en facebook "+ data);
		return data;
	}

	public static IServiceGateway getInstance(String serverIP,int serverPort, String serverName) {
		if(instance == null) {
			instance = new FacebookServiceGateway(serverIP, serverPort, serverName);
		}
		return instance;
	}

}
