
package com.flakks.spelling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class Connection extends Thread {
	private Socket socket;
	
	public Connection(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
				
				writer.write(new RequestHandler(bufferedReader.readLine()).process());
				writer.write("\n");
				writer.flush();
			} finally {
				socket.close();
			}
		} catch(IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}

public class Server {
	ServerSocket serverSocket;

	public void start() throws IOException {
		serverSocket = new ServerSocket(12182, 1024, InetAddress.getLocalHost());
		
		while(true) {
			new Connection(serverSocket.accept()).start();
		}
	}
}