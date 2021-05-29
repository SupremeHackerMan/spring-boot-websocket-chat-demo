package com.example.websocketdemo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatRooms {
	private HashMap<String, ArrayList<String>> rooms;
	
	//initializes the map
	public void initChatRooms() {
		rooms = new HashMap<String, ArrayList<String>>();
    }
    public void createRoom(String user, String roomName) {
    	ArrayList<String> tmp = new ArrayList<String>();
    	tmp.add(user);
    	rooms.put(roomName, tmp);
    }
    public void joinRoom(String user, String roomName) {
    	ArrayList<String> tmp = rooms.get(roomName);
    	tmp.add(user); 
    }
   
}
