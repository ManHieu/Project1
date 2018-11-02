package DNSClient.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class DnsClient {
	private final int PORT_SERVER = 53;
	private InetAddress serverHost;
	public DatagramSocket socket;
	
	public DnsClient(String server) {
		// TODO Auto-generated constructor stub
		try {
			this.serverHost = InetAddress.getByName(server);
			this.socket = new  DatagramSocket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean sendMessage(String message) {
		try {
			
			byte[] sendByte = DnsMessage.encodeDNSMessage(message);
			DatagramPacket sendPacket = new DatagramPacket(
					sendByte, sendByte.length, serverHost, PORT_SERVER);
			socket.send(sendPacket);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
		
	public byte[] receiveMessage() {
		try {
			byte[] receiveByte = new byte[1024];
			DatagramPacket recPacket = new DatagramPacket(receiveByte, receiveByte.length);
			socket.receive(recPacket);
			
			return receiveByte;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
		
	
}
