package com.example.skolaback.service;

import com.example.skolaback.model.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications(String jmbg);
}
