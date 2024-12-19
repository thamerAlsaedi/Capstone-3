package com.example.marketing.Controller;


import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.Model.Notification;
import com.example.marketing.Service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/get")
    public ResponseEntity<List<Notification>> getNotification() {
        return ResponseEntity.ok(notificationService.getNotifications());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Integer id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNotification(@RequestBody @Valid Notification notification) {
        notificationService.addNotification(notification);
        return ResponseEntity.status(200).body(new ApiResponse("successfully added Notification"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateNotification(@RequestBody @Valid Notification notification) {
        notificationService.updateNotification(notification);
        return ResponseEntity.status(200).body(new ApiResponse("successfully updated Notification"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteNotification(@PathVariable Integer id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted Notification"));
    }
}
