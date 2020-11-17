package me.chatroom.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    /** 訊息種類 */
    private ChatType type;
    /** 訊息發送者的名稱 */
    private String sender;
    /** 訊息內容 */
    private String content;

    /**
     * 訊息種類Enum
     */
    public enum ChatType {
        CHAT,
        JOIN,
        LEAVE
    }
}
