package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.ChatMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {
	
	/*
	//old way without @DestinationVariable
	private SimpMessagingTemplate template;
	
	@Autowired
	public ChatController(SimpMessagingTemplate template) {
			this.template = template;
	}
	*/
	
	
	//if a message is sent to /chat.sendMessage, then the sendMessage() is called
	//the return value is broadcast to all subscribers of @sendTo("/topic/{rmName}")
	//@DestinationVariable lets you dynamically change the path variable in @Sendto
    @MessageMapping("/chat/{rmName}.sendMessage")
    @SendTo("/topic/{rmName}") 
    public ChatMessage sendMessage(@DestinationVariable String rmName, @Payload ChatMessage chatMessage) {
    	//this.template.convertAndSend("/topic/" +chatMessage.getRoomName(), chatMessage); 
        return chatMessage;
    }

	@MessageMapping("/chat/{rmName}.addUser")
	@SendTo("/topic/{rmName}") 
	public ChatMessage addUser(@DestinationVariable String rmName, @Payload ChatMessage
									chatMessage, SimpMessageHeaderAccessor headerAccessor) { 
		// Add username in web socket session
		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender()); 
		
		return chatMessage; 
	}

}
