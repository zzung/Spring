package com.coderby.myapp.websocket.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ChatRepository {

	private Map<Integer, ChattingRoom> roomMap = new HashMap<>();
	
	public List<ChattingRoom> loadAllRooms(){
		List<ChattingRoom> rooms = new ArrayList<>(roomMap.values());
		Collections.reverse(rooms); //최신 채팅방이 맨위로 올라가게 
		return rooms;
	}
	
	public ChattingRoom selectRoom(int roomId) {
		return roomMap.get(roomId);
	}
	
	public ChattingRoom createChattingRoom(String name) {
		ChattingRoom room  = ChattingRoom.createRoom(name);
		roomMap.put(room.getRoomId(), room);
		return room;
	}
	
	
}
