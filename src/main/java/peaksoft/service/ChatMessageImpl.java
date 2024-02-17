package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import peaksoft.chat.ChatMessage;
import peaksoft.chat.MessageType;
import peaksoft.repo.ChatMessageRepo;

@Service
@RequiredArgsConstructor
public class ChatMessageImpl implements ChatMessageService{

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepo messageRepository;

    @Override
    public void sendMessage(ChatMessage chatMessage) {
        ChatMessage messageEntity = new ChatMessage();
        messageEntity.setSender(chatMessage.getSender());
        messageEntity.setContent(chatMessage.getContent());
        messageEntity.setType(MessageType.CHAT);
        messageRepository.save(messageEntity);

        // Отправка обработанного сообщения всем подключенным клиентам
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }

    @Override
    public void addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String username = chatMessage.getSender();
        String content = "Пользователь " + username + " присоединился к чату";
        ChatMessage response = new ChatMessage(MessageType.JOIN, content, username);

        // Сохранение пользователя в базе данных
        ChatMessage userEntity = new ChatMessage();
        userEntity.setSender(username);
        userEntity.setContent("присоединился к чату");
        userEntity.setType(MessageType.JOIN);
        messageRepository.save(userEntity);

        // Отправка уведомления всем подключенным клиентам
        messagingTemplate.convertAndSend("/topic/public", response);
    }
}
