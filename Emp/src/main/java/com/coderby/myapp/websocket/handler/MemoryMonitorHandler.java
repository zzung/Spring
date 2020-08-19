package com.coderby.myapp.websocket.handler;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MemoryMonitorHandler extends TextWebSocketHandler implements InitializingBean {

	private Set<WebSocketSession> sessionSet = new HashSet<>();
	
	public MemoryMonitorHandler() {
		System.out.println("소켓 핸들러 인스턴스 생성");
	}

	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
		System.out.println("세션 추가");
	}



	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					try {
						MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
						MemoryUsage memoryUsage = memoryBean.getHeapMemoryUsage();
						long time = System.currentTimeMillis()+32400000;
						int committed = (int)(memoryUsage.getCommitted()/(1024*1024));
						int max = (int)(memoryUsage.getMax()/(1024*1024));
						int used = (int)(memoryUsage.getUsed()/(1024*1024));
						sendMessage("[{\"time\":"+time+", \"used\":"+used+"}," 
								+"{\"time\":"+time+", \"max\":"+max+"},"
								+"{\"time\":"+time+", \"committed\":"+committed+"}]");
						Thread.sleep(5000);
					}catch(InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
		thread.start();
	} 

	private void sendMessage(String message) {
		for(WebSocketSession session : this.sessionSet) {
			if(session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
