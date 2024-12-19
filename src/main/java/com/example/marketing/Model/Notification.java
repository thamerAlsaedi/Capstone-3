package com.example.marketing.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Description is mandatory")
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    @Column(nullable = false)
    private String notification_description;

    @NotEmpty(message = "Notification to user is mandatory")
    @Size(max = 100, message = "Notification to user must not exceed 100 characters")
    @Column(nullable = false)
    private String notification_ToUser;

    @PastOrPresent(message = "CreateAt date cannot be in the future")
    private LocalDateTime notification_createAt = LocalDateTime.now();
}

