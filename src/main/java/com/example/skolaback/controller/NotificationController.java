package com.example.skolaback.controller;

import com.example.skolaback.model.dto.school.SchoolResponseDTO;
import com.example.skolaback.model.entity.Notification;
import com.example.skolaback.security.permission.IsLoggedIn;
import com.example.skolaback.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifikacije")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{jmbg}")
    @IsLoggedIn
    public List<Notification> getNotifications(@PathVariable String jmbg) {
        return notificationService.getNotifications(jmbg);
    }
}
