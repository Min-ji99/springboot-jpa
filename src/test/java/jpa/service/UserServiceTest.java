package jpa.service;

import jpa.domain.dto.UserRequest;
import jpa.domain.dto.UserResponse;
import jpa.domain.entity.User;
import jpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {
    private UserRepository userRepository= Mockito.mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    void setup(){
        userService=new UserService(userRepository);
    }
    @Test
    @DisplayName("회원 등록 성공 메시지가 나오는지")
    void addTest(){
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l, "minji", "12345"));

        UserResponse userResponse=userService.add(new UserRequest("minji", "12345"));
        assertEquals("회원등록 성공", userResponse.getMessage());
    }

}