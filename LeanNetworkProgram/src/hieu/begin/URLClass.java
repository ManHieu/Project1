package hieu.begin;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLClass {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://toidicodedao.com/2015/07/30/c-la-ngon-ngu-tuyet-voi-nhat-java-php-c-c-ruby-chi-toan-la-thu-re-tien/");
//			System.out.println(url.getProtocol());
//			System.out.println(url.getPort());
//			System.out.println(url.getHost());
//			System.out.println(url.getQuery());
			URLConnection conn = url.openConnection(); // mở liên kết đến URL
			if(conn == null) {
				System.out.println("Không kết nối được!!!");
				return ;
			}
			
			InputStream is = conn.getInputStream(); // tạo một input để có thể đọc được dữ liệu
			BufferedInputStream bis = new BufferedInputStream(is);
			
			int i = bis.read();
			while(i != -1) {
				
				System.out.print((char)i);
				i = bis.read();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("lỗi???");
			e.printStackTrace();
		}
	}
}
