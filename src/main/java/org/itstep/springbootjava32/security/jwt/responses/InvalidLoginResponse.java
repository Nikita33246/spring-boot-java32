package org.itstep.springbootjava32.security.jwt.responses;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid rest username";
        this.password = "Invalid rest password";
    }
}
