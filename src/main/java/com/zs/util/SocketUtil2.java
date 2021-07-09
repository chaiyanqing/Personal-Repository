package com.zs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.zs.service.ICommonService;

public class SocketUtil2 {
	
	private static String address = null;
	private static Integer port = null;
	
	public SocketUtil2() {
		Properties prop = PropertiesUtil.getProperties("sys.properties");
		address = prop.getProperty("address");
		port = Integer.parseInt(prop.getProperty("port"));
	}
	
	
	public static void main(String[] args) throws IOException {
		Properties prop = PropertiesUtil.getProperties("sys.properties");
		address = prop.getProperty("address");
		port = Integer.parseInt(prop.getProperty("port"));
		System.out.println("Client Start...");
		try {
			Socket socket = new Socket(address, port);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());;
			String readline = "";
			while(!"bye".equals(readline)) {
				// 向客户端发送消息
				try {
					TimeUnit.SECONDS.sleep(2);
					writer.println("{\"type\":\"device\", \"value\":\"123\"}");
					writer.flush();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 获取服务端反馈消息
				readline = socketIn.readLine();
				if(readline != null) {
					System.out.println("Server return msg is:  " + readline);
				}
			}
			socketIn.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Client shutdown...");
	}
}
