package com.example.marketing.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerBookingCountDTO {

    private String influencerName;
    private Long bookingCount;
}
