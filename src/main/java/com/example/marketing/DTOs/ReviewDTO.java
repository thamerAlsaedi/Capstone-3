package com.example.marketing.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {

    private String reviewDescription;
    private Integer reviewRating;
}
