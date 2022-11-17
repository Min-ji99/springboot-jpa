package jpa.controller;

import jpa.domain.dto.UserRequest;
import jpa.domain.dto.UserResponse;
import jpa.repository.UserRepository;
import jpa.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    UserService userService;
    public UserRestController(UserService userService){
        this.userService=userService;
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @PostMapping("")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest dto){
        return ResponseEntity.ok().body(userService.add(dto));
    }
}
