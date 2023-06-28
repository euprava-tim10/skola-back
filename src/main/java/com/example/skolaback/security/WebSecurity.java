package com.example.skolaback.security;

import com.example.skolaback.model.entity.Contest;
import com.example.skolaback.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.skolaback.security.AuthHelper.authUser;

@Component
public class WebSecurity {

    private final ContestService contestService;

    public WebSecurity(ContestService contestService) {
        this.contestService = contestService;
    }

    public boolean checkSchoolAdmin(long id) {
        return authUser().getSchoolId() == id;
    }

    public boolean checkContestAdmin(long id) {
        Contest contest = contestService.getById(id);
        return authUser().getSchoolId() == contest.getSchool().getId();
    }
}
