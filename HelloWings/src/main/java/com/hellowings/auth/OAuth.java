package com.hellowings.auth;

import com.hellowings.auth.dto.AccessTokenResponseDTO;
import com.hellowings.auth.dto.OAuthUserResponseDTO;

public interface OAuth {
    AccessTokenResponseDTO getToken(String code);

    OAuthUserResponseDTO getUserInfo(String token);
}
