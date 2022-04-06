package com.example.springboot_social_network.service;

import com.example.springboot_social_network.entity.Role;
import com.example.springboot_social_network.entity.User;
import com.example.springboot_social_network.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSender mailSender;

    public UserService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        sendMessage(user);
//        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
//            String message = String.format(
//                    "Hello, %s!\n" +
//                            "Welcome to new social network!\n" +
//                            "Please, visit next link: http://localhost:8080/activate/%s",
//                    user.getUsername(), user.getActivationCode());
//
//            mailSender.sendSimpleEmail(user.getEmail(), "Activation code", message);
//        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(password);
        }

        userRepository.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    private void sendMessage(User user) {
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            String message = String.format(
                    "Hello, %s!\n" +
                            "Welcome to new social network!\n" +
                            "Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode());

            mailSender.sendSimpleEmail(user.getEmail(), "Activation code", message);
        }
    }
}