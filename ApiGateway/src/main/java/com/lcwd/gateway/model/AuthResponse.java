package com.lcwd.gateway.model;


import lombok.*;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private  long expireAt;
    private Collection<String> authorities;
}
