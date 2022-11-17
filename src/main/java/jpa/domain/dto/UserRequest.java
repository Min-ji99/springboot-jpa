package jpa.domain.dto;

import jpa.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;

    public User toEntity(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
