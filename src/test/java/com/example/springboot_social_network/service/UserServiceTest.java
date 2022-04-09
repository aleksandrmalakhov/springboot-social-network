package com.example.springboot_social_network.service;

import com.example.springboot_social_network.entity.Role;
import com.example.springboot_social_network.entity.User;
import com.example.springboot_social_network.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    private final UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender mailSender;

    @Autowired
    UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void addUser() {
        User user = new User();
        user.setEmail("some@mail.ru");

        boolean isUserCreated = userService.addUser(user);

        assertTrue(isUserCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        verify(userRepository, times(1)).save(user);
        verify(mailSender, times(1)).sendSimpleEmail(eq(user.getEmail()), anyString(), anyString());
    }

    @Test
    void addUserFail() {
        User user = new User();
        user.setUsername("John");

        doReturn(new User())
                .when(userRepository)
                .findByUsername("John");

        boolean isUserCreated = userService.addUser(user);

        assertFalse(isUserCreated);

        verify(userRepository, times(0)).save(any(User.class));
        verify(mailSender, times(0)).sendSimpleEmail(anyString(), anyString(), anyString());
    }

    @Test
    void activateUser() {
        User user = new User();
        user.setActivationCode("bingo");

        doReturn(user)
                .when(userRepository)
                .findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        assertTrue(isUserActivated);
        assertNull(user.getActivationCode());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void activateUserFail() {
        boolean isUserActivated = userService.activateUser("activate");

        assertFalse(isUserActivated);
        verify(userRepository, times(0)).save(any(User.class));
    }
}