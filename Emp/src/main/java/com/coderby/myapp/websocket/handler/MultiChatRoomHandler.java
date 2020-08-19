package com.coderby.myapp.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.coderby.myapp.websocket.model.ChatRepository;
import com.coderby.myapp.websocket.model.ChattingMessage;
import com.coderby.myapp.websocket.model.ChattingRoom;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MultiChatRoomHandler extends TextWebSocketHandler {

	//
	private static final Logger logger = LoggerFactory.getLogger(MultiChatRoomHandler.class);
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	//objectMapper : String 값을 키-값(객체)으로 매칭시켜줌

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("메시지 전송 = {} : {}", session, message.getPayload());
		ChattingMessage msg = objectMapper.readValue(message.getPayload(), ChattingMessage.class);
		System.out.println(msg);
		ChattingRoom room = chatRepository.selectRoom(msg.getRoomId());
		room.handlerMessage(session, msg); //그 채팅방에 있는 사람들에게만 전달
	}
	
//	ChattingMessage.class: 결과값으로 나와야 되는것
//	readValue()가 커맨드객체의 역할을 함 (이름-값을 매칭시켜줌)
	
	
}
