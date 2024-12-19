package com.example.marketing.DTOs;

import com.example.marketing.Model.Company;
import com.example.marketing.Model.Influencer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingOneTimeDTO {

    private Integer id;
    private LocalDateTime booking_date;

    private String booking_status ;

    private LocalDate advertisementStartDate;








}
