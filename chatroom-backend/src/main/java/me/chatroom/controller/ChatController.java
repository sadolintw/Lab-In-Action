package me.chatroom.controller;

import me.chatroom.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 接收Client送來的WebSocket訊息及推送給前端的訊息
 */
@CrossOrigin
@Controller
public class ChatController {

    public String index(){
        return "index";
    }

    /**
     * 處理前端加入聊天室的訊息，並把訊息推送給前端
     */
    @MessageMapping("/join")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // 把username加入WebSocket的Session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage; // 返回時會將訊息送至/topic/public
    }

    /**
     * 處理前端送來的聊天訊息，並把訊息推送給前端
     */
    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage; // 返回時會將訊息送至/topic/public
    }

}
