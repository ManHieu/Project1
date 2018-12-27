package hieu.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	Socket clientSocket;
	DataInputStream dis;
	DataOutputStream dos;
	
	public Client(String host, int port) {
		// TODO Auto-generated constructor stub
		try {
			clientSocket = new Socket(host, port);
			dis = new DataInputStream(clientSocket.getInputStream());
			dos = new DataOutputStream(clientSocket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendRequest(String request) {
		try {
			dos.writeUTF(request);
			dos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendObject(Object ob) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(dos);
			oos.writeObject(ob);
			oos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int receiveAuthor() {
		try {
			int flag;
			flag = dis.readInt();
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	public Object receiveAnswer() {
		try {
			Object ob = new Object();
			ObjectInputStream ois = new ObjectInputStream(dis);
			ob = ois.readObject();
			return ob;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void close() {
		try {
			dos.close();
			dis.close();
			clientSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
