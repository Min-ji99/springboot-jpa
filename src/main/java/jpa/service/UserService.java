package jpa.service;

import jpa.domain.dto.UserRequest;
import jpa.domain.dto.UserResponse;
import jpa.domain.entity.User;
import jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResponse getUserById(Long id){
        Optional<User> optUser=userRepository.findById(id);
        if (optUser.isEmpty()){
            return new UserResponse(id, "", "해당 ID의 유저가 없습니다.");
        }
        User user=optUser.get();
        return new UserResponse(user.getId(), user.getUsername(), "회원등록 성공");
    }

    public UserResponse add(UserRequest dto) {
        User user=dto.toEntity();
        Optional<User> optUser=userRepository.findByUsername(dto.getUsername());
        if(!optUser.isEmpty()){
            return UserResponse.builder()
                    .message("username이 중복입니다.").build();
        }
        User savedUser=userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), "회원등록 성공");
    }
}
