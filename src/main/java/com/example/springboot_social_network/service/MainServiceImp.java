package com.example.springboot_social_network.service;

import com.example.springboot_social_network.controller.ControllerUtils;
import com.example.springboot_social_network.entity.Message;
import com.example.springboot_social_network.repository.MessageRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class MainServiceImp implements MainService {
    @Value("${upload.path}")
    private String uploadPath;
    private final MessageRepository repository;

    public MainServiceImp(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void main(@NonNull Model model, String filter) {
        Iterable<Message> messages = (filter != null && !filter.isBlank()) ?
                repository.findByTag(filter) :
                repository.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
    }

    @Override
    public void add(Model model, Message message, BindingResult bindingResult, MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            saveFile(message, file);

            model.addAttribute("message", null);
            repository.save(message);
        }

        Iterable<Message> messages = repository.findAll();
        model.addAttribute("messages", messages);
    }

    @Override
    public void updateMessage(Message message, MultipartFile file) throws IOException {
        saveFile(message, file);
        repository.save(message);
    }

    private void saveFile(
            @Valid Message message,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }
    }
}
