package hieu.begin;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIPDoMain {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] adress = InetAddress.getAllByName("Google.com");
		for(InetAddress i : adress) {
			System.out.println(i.getHostName());
			System.out.println(i.getHostAddress());
		}
		
	}

}
