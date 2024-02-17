package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.chat.ChatMessage;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
}
