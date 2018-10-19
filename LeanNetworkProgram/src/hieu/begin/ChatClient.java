package hieu.begin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) throws IOException {
		Socket sk = new Socket("localhost", 3333);
		
		DataInputStream dis = new DataInputStream(sk.getInputStream());
		DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String st = sc.nextLine();
			dos.writeUTF(st);
			dos.flush();
			
			if(st.equals("q")) break;
			
			String str = dis.readUTF();
			System.out.println("Server: " + str);
		}
		
		dos.close();
		dis.close();
		sk.close();
	}
}
