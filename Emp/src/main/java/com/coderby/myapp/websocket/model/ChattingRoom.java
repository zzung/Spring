package com.coderby.myapp.websocket.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChattingRoom {
	
	public static ArrayList<Integer> roomIds =new ArrayList<>(); //중복안되게 하려고  지정
	private int roomId;
	private String roomName;
	private List<WebSocketSession> sessions = new ArrayList<>(); //나한테 들어온 웹소켓
	
	public static ChattingRoom createRoom(String name) { //name : 방이름
		ChattingRoom room = new ChattingRoom();
		ChattingRoom.roomIds.add(ChattingRoom.roomIds.size()+1);
		room.roomId = ChattingRoom.roomIds.size();
//		room.roomId = UUID.randomUUID().toString(); 아이디값을 중복이 안되게끔 뽑아냄 : 문자열 (시리얼값으로 나옴 --> 받아줄 변수값을 String으로 바꿔주면 사용가능)
		room.roomName = name;
		return room;
	}
	
	public void handlerMessage(WebSocketSession session, ChattingMessage message) throws IOException {
		if(message.getMessageType().equals("Enter")) {
			sessions.add(session); 
			message.setMessage(message.getUser()+"님이 입장하셨습니다.");
		} else if(message.getMessageType().equals("Leave")) {
			sessions.remove(session);
			message.setMessage(message.getUser()+"님이 퇴장하셨습니다.");
		} else {
			message.setMessage(message.getUser()+" : "+message.getMessage());
		}
		send(message);
	}
	
	private void send(ChattingMessage message) throws IOException{
		TextMessage txtMessage = new TextMessage(message.getMessage());
		for(WebSocketSession s : sessions) { //내방에 들어온 세션들
			s.sendMessage(txtMessage);
		}
	}

	public Integer getRoomId() {
		return this.roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return this.roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getSize() {
		return this.sessions.size();
	}
	
}
