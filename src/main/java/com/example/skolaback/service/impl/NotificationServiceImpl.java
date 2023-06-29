package com.example.skolaback.service.impl;

import com.example.skolaback.model.entity.Notification;
import com.example.skolaback.repository.NotificationRepository;
import com.example.skolaback.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getNotifications(String jmbg) {
        return notificationRepository.getNotificationsByStudent_Jmbg(jmbg);
    }
}
