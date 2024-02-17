package peaksoft.chat;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String content;
    private String sender;

    public ChatMessage(MessageType type, String content, String sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    public ChatMessage() {
    }
}
