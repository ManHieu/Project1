package hieu.begin;

import java.net.InetAddress;

public class GetIPLocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress myHost = InetAddress.getLocalHost();
			System.out.println(myHost.getHostAddress());
			System.out.println(myHost.getHostName());
			System.out.println(myHost.getCanonicalHostName());
			System.out.println(myHost.getAddress());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
