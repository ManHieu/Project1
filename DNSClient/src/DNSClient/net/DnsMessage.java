package DNSClient.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

public class DnsMessage{


	public static byte[] encodeDNSMessage(String message) {
		byte[] dnsMessage = new byte[1024];
		try {
			// Tạo một stream để ghi ra 1 byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// Tạo một stream để cho phép ghi dữ liệu dạng 2 byte một
			DataOutputStream dos = new DataOutputStream(baos);

			// Phần header cho DNSmessage
			// Identifier
			dos.writeShort(0x1111);
			// Flags
			dos.writeShort(0x0100);
			// QDcount
			dos.writeShort(0x0001);
			// ANcount
			dos.writeShort(0x0000); 
			// NScount
			dos.writeShort(0x0000);
			// ARcount
			dos.writeShort(0x0000);
			// Phần Question
			// Chuyển phần domain name về dạng chuẩn
			String[] domainParts = message.split("\\.");
			for(String part : domainParts) {
				byte[] domainBytes = part.getBytes("UTF-8");
				dos.writeByte(domainBytes.length);
				dos.write(domainBytes);
			}
			// Báo đã kết thúc phần domain name
			dos.writeByte(0x00);
			// Loại A
			dos.writeShort(0x0001);
			// Lớp truy vấn Internet
			dos.writeShort(0x0001);

			dnsMessage = baos.toByteArray();

			return dnsMessage;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	public static String decodeDNSMessage(byte[] dnsMessage) {
		ByteArrayInputStream bais = new ByteArrayInputStream(dnsMessage);
		DataInputStream dis = new DataInputStream(bais);

		try {
			// Hearder
			byte[] hearder = new byte[12];
			dis.readFully(hearder);

			// Question
			ArrayList<String> question = new ArrayList<>();
			int partLen = 0;
			while((partLen = dis.readByte()) > 0) {
				byte[] partQuestion = new byte[partLen];
				for(int i = 0; i < partLen; i++) {
					partQuestion[i] = dis.readByte();
				}
				question.add(new String(partQuestion, "UTF-8"));	
			}
			short questionType = dis.readShort();
			short questionClass = dis.readShort();
			
			// Answers
			short field = dis.readShort();
			short typeAnswer = dis.readShort();
			short classAnswer = dis.readShort();
			int TTL = dis.readInt();
			short addLen = dis.readShort();
			String addr = new String();
			for(int i = 0; i < addLen; i++) {
				addr = addr + String.format("%d", (dis.readByte() & 0xFF)) + ".";
			}
			
			return addr;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
