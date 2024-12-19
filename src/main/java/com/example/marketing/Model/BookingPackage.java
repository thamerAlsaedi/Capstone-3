package com.example.marketing.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class BookingPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Booking date cannot be null")
    @PastOrPresent(message = "Booking date cannot be in the future")
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime bookingDate = LocalDateTime.now();

    @NotNull(message = "Booking total price cannot be null")
    @Positive(message = "Booking total price must be greater than 0")
    @Column(columnDefinition = "double not null")
    private Double bookingTotalPrice;

    @Pattern(regexp = "PENDING|IN PROCESS|COMPLETED|REJECT", message = "Booking status must be one of PENDING, IN PROCESS, COMPLETED or REJECT")
    @Column(columnDefinition = "varchar(20) not null")
    private String bookingStatus= "PENDING";

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate advertisementStartDate;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private Influencer influencer;

    @ManyToOne
    @JsonIgnore
    private Package packages;
}
