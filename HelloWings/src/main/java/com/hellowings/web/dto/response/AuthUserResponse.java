package com.hellowings.web.dto.response;

import com.hellowings.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthUserResponse {
    private final Long userId;
    private final String avatarUrl;
    private final String name;
    private final String jwt;

    public static AuthUserResponse of(User user, String token) {
        return AuthUserResponse.builder()
                .userId(user.getId())
                .avatarUrl(user.getAvatarUrl())
                .name(user.getName())
                .jwt(token)
                .build();
    }
}
