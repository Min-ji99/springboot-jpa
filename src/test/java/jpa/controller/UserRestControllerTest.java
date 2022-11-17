package jpa.controller;

import jpa.domain.dto.UserResponse;
import jpa.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Get /api/v1/users 테스트")
    void getUserByIdTest() throws Exception {
        long id=2l;

        given(userService.getUserById(id))
                .willReturn(new UserResponse(id, "minji", "회원등록성공"));

        String url="/api/v1/users/"+id;
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message").exists())
                .andDo(print());
        verify(userService).getUserById(id);
    }
    @Test
    @DisplayName("해당 id가 없을 때 message 잘 나오는지 확인")
    void getUserByIdTest2() throws Exception {
        long id=100l;

        given(userService.getUserById(id))
                .willReturn(new UserResponse(id, "", "해당 ID의 유저가 없습니다."));

        String url="/api/v1/users/"+id;
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message").exists())
                .andDo(print());
        verify(userService).getUserById(id);
    }
    @Test
    @DisplayName("Post /api/v1/users 테스트")
    void addUserTest(){

    }
}