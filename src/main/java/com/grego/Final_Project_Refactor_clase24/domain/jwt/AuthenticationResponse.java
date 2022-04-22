package com.grego.Final_Project_Refactor_clase24.domain.jwt;

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;

    }
}
