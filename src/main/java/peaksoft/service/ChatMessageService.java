package peaksoft.service;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import peaksoft.chat.ChatMessage;

public interface ChatMessageService {
    void sendMessage(ChatMessage chatMessage);
    void addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor);
}
