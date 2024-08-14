package org.itstep.springbootjava32.security.jwt.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenSuccessResponse {
    private boolean success;
    private String token;
}
