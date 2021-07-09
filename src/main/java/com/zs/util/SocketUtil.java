package com.zs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.zs.service.ICommonService;

public class SocketUtil {
	
	private static String address = null;
	private static Integer port = null;
	
	public SocketUtil() {
		Properties prop = PropertiesUtil.getProperties("sys.properties");
		address = prop.getProperty("address");
		port = Integer.parseInt(prop.getProperty("port"));
	}
	
	public static void read(ICommonService iCommonService) {
		System.out.println("Socket Start...");
		try {
			Socket socket = new Socket(address, port);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readline = "";
			while(!"bye".equals(readline)) {
				// 读取客户端消息
				readline = socketIn.readLine();
				if(readline != null) {
					System.out.println("client socketIn str is :  " + readline);
					iCommonService.doSomething(readline);
				}
			}
			socketIn.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static ServerSocket server = null;
	
	public static ServerSocket getServerSocket() {
		if(server == null) {
			try {
				server = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return server;
	}
	
	
	public static void write(String message) {
		server = getServerSocket();
		Socket socket = null;
		PrintWriter writer = null;
		try {
			socket = server.accept();
			writer = new PrintWriter(socket.getOutputStream());
			
			writer.print(message);
			writer.flush();
			
			writer.close();
			socket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Server Start...");
		Properties prop = PropertiesUtil.getProperties("sys.properties");
		int port = Integer.parseInt(prop.getProperty("port"));
		server = new ServerSocket(port);
		int count =0;//记录客户端的数量
		Socket socket = null;
		while (true) {
			socket = server.accept();
			SocketThread t2 = new SocketThread(socket);
			new Thread(t2).start();
//			
//			SocketThread1 t1 = new SocketThread1(socket);
//			t1.start();
			
			count++;
			System.out.println("客户端连接的数量："+count);
		}
//		server.close();
//		System.out.println("服务退出。。。");
		
	}
	
}

class SocketThread implements Runnable{

	private Socket socket;
	
	public SocketThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			Socket iSocket = socket;
			PrintWriter writer = new PrintWriter(iSocket.getOutputStream());;
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readLine = "";
			while (!"bye".equals(readLine)) {
				readLine = socketIn.readLine();
				System.out.println("接收客户端的消息：" + readLine);
				writer.println("The message received.");
				writer.flush();
			}
			writer.close();
			socketIn.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


class SocketThread1 extends Thread{

	private Socket socket;
	
	public SocketThread1(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());;
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readLine = "";
			while (!"bye".equals(readLine)) {
				readLine = socketIn.readLine();
				System.out.println("接收客户端的消息：" + readLine);
				writer.println("The message received.");
				writer.flush();
			}
			writer.close();
			socketIn.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}