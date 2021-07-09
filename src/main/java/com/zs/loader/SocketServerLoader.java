package com.zs.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.zs.service.AbodyplusService;
import com.zs.util.PropertiesUtil;

public class SocketServerLoader implements ApplicationListener<ApplicationEvent>  {
	private Logger logger = Logger.getLogger(SocketServerLoader.class);
	private static ServerSocket server = null;
	private static Integer port = null;
	private static Boolean isStart = false;
	@Resource
	private AbodyplusService abodyplusService;
	
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

	public void onApplicationEvent(ApplicationEvent arg0) {
//		System.out.println("EventName: "+arg0.getSource().getClass().getSimpleName());
		if(!isStart) {
			isStart = true;
			Properties prop = PropertiesUtil.getProperties("sys.properties");
			port = Integer.parseInt(prop.getProperty("port"));
			server = getServerSocket();
			new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("Socket server started, port is " + port + ".");
					Socket socket = null;
					int clientCount = 0;
					while(true) {
						try {
							socket = server.accept();
							SocketServerThread thread = new SocketServerThread(socket, abodyplusService);
							thread.start();
							clientCount ++;
							logger.info("客户端连接的数量：" + clientCount);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}else {
			
		}
		
	}

	
}

class SocketServerThread extends Thread{
	
	private AbodyplusService abodyplusService;
	private Logger logger = Logger.getLogger(SocketServerThread.class);
	
	private Socket socket;
	public SocketServerThread(Socket socket, AbodyplusService abodyplusService) {
		this.socket = socket;
		this.abodyplusService = abodyplusService;
	}
	
	@Override
	public void run() {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());;
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readLine = "";
			while (!"bye".equals(readLine)) {
				readLine = socketIn.readLine();
				if(readLine != null) {
					logger.info("接收客户端的消息：" + readLine);
					abodyplusService.doSomething(readLine);
					writer.println("[Success] The message received.");
					writer.flush();
				}
			}
			writer.close();
			socketIn.close();
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		super.run();
	}
}
