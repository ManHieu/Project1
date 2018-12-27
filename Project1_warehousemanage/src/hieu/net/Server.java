package hieu.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import hieu.model.Category;
import hieu.model.Product;
import hieu.model.User;
import hieu.service.CategoryService;
import hieu.service.ProductService;
import hieu.service.UserService;

public class Server {
	//	ServerSocket serverSocket;
	//	public Server() {
	//		// TODO Auto-generated constructor stub
	//		try {
	//			serverSocket = new ServerSocket(3333);	
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//			e.printStackTrace();
	//		}
	//	}
	//	
	//	public void waitForConn() {
	//		while(true) {
	//			try {
	//				Socket socket = serverSocket.accept();
	//				DataInputStream dis = new DataInputStream(socket.getInputStream());
	//				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
	//				String request = dis.readUTF();
	//				if(request.equals("login")) {
	//					ObjectInputStream ois = new ObjectInputStream(dis);
	//					User user = (User) ois.readObject();
	//					UserService service = new UserService();
	//					int flag = service.find(user);
	//					dos.writeInt(flag);
	//					dos.flush();
	//					
	//					dos.close();
	//					ois.close();
	//					dis.close();
	//					socket.close();
	//				}
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}
	//	}

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			System.out.println("Binding to port " + 3333 + ", please wait  ...");
			serverSocket = new ServerSocket(3333);
			System.out.println("Server started: " + serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					String request = dis.readUTF();
					if(request.equals("login")) {
						ObjectInputStream ois = new ObjectInputStream(dis);
						User user = (User) ois.readObject();
						UserService service = new UserService();
						int flag = service.find(user);
						dos.writeInt(flag);
						dos.flush();

						dos.close();
						ois.close();
						dis.close();
						socket.close();
					}
					if(request.equals("listCate")) {
						ObjectOutputStream oos = new ObjectOutputStream(dos);
						
						Vector<Category> list = CategoryService.getCategory();
						oos.writeObject(list);
						oos.flush();
						
						oos.close();
						dos.close();
						dis.close();
						socket.close();
					}
					
					if(request.equals("listProduct")) {
						int idCate = Integer.parseInt(dis.readUTF());
						System.out.println(idCate);
						Vector<Product> list = ProductService.getProduct(idCate);
						ObjectOutputStream oos = new  ObjectOutputStream(dos);
						
						oos.writeObject(list);
						oos.flush();
						
						oos.close();
						dos.close();
						dis.close();
						socket.close();
						
					}
					} catch (Exception e) {
						System.err.println(" Connection Error: " + e);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				if (serverSocket != null) {
					serverSocket.close();
				}
			}
		}
	}
