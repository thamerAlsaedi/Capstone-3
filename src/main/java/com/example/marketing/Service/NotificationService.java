package com.example.marketing.Service;

import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.Model.Notification;
import com.example.marketing.Repostiroy.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Internal;
import org.hibernate.type.descriptor.sql.internal.NamedNativeOrdinalEnumDdlTypeImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Integer id) {
        return notificationRepository.findById(id).orElseThrow(()-> new ApiException("Notification not found"));
    }

    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public void updateNotification(Notification notification) {
        Notification oldNot = notificationRepository.findById(notification.getId()).orElseThrow(()-> new ApiException("Notification not found"));

        oldNot.setNotification_description(notification.getNotification_description());
        oldNot.setNotification_ToUser(notification.getNotification_ToUser());

        notificationRepository.save(oldNot);
    }
    public void deleteNotification(Integer id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(()-> new ApiException("Notification not found"));
        notificationRepository.deleteById(id);
    }
}
