package peaksoft.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import peaksoft.service.ChatMessageService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage(@Payload ChatMessage chatMessage) {
         chatMessageService.sendMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
         chatMessageService.addUser(chatMessage, headerAccessor);
    }
}
