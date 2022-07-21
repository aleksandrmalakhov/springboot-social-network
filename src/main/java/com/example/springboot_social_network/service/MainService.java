package com.example.springboot_social_network.service;

import com.example.springboot_social_network.entity.Message;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MainService {
    void main(Model model, String filter);
    void add(Model model, Message message, BindingResult bindingResult, MultipartFile file) throws IOException;
    void updateMessage(Message message, MultipartFile file) throws IOException;
}