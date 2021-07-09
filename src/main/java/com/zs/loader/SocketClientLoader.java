package com.zs.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;

import com.zs.service.AbodyplusService;
import com.zs.util.PropertiesUtil;

public class SocketClientLoader /* implements ApplicationListener<ApplicationEvent> */{
	
	@Resource
	private AbodyplusService abodyplusService;
	
	public void onApplicationEvent(ApplicationEvent arg0) {
		System.out.println("Socket client start.");
		Properties prop = PropertiesUtil.getProperties("sys.properties");
		String address = prop.getProperty("address");
		int port = Integer.parseInt(prop.getProperty("port"));
		try {
			Socket socket = new Socket(address, port);
			BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readline = socketIn.readLine();
			while(!"bye".equals(readline)) {
				// 读取客户端消息
				if(readline != null) {
					System.out.println("client socketIn str is :  " + readline);
					abodyplusService.doSomething(readline);
				}
				readline = socketIn.readLine();
			}
			socketIn.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Socket client close.");
	}

}
