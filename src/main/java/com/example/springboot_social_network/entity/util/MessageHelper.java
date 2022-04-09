package com.example.springboot_social_network.entity.util;

import com.example.springboot_social_network.entity.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}