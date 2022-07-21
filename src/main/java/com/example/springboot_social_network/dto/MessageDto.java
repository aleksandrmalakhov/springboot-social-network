package com.example.springboot_social_network.dto;

import com.example.springboot_social_network.entity.Message;
import com.example.springboot_social_network.entity.User;
import com.example.springboot_social_network.entity.util.MessageHelper;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class MessageDto {
    private long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private long likes;
    private boolean meLiked;

    public MessageDto(@NonNull Message message, long likes, boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}