package hieu.begin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(3333);
		Socket socket = serverSocket.accept();
		
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		
		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
			
			String st = dis.readUTF();
			if(st.equals("q")) break;
			System.out.println("Client: " + st);
			
			String st2 = sc.nextLine();
			dos.writeUTF(st2);
			dos.flush();
		}
		dos.close();
		dis.close();
		socket.close();
		serverSocket.close();
	}

}
