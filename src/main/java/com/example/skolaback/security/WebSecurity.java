package com.example.skolaback.security;

import org.springframework.stereotype.Component;

import static com.example.skolaback.security.AuthHelper.authUser;

@Component
public class WebSecurity {

    public boolean checkSchoolAdmin(long id) {
        return authUser().getSchoolId() == id;
    }
}
