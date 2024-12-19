package com.example.marketing.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class InfluencerRatingDTO {
    private Long influencerId;
    @NotEmpty(message = "Influencer name cannot be Empty")
    @Size(min = 2, max = 100, message = "Influencer name must be between 2 and 100 characters")
    private String name;
    private Double averageRating;
    private Long totalReviews;


    public InfluencerRatingDTO(Long influencerId, String name, Double averageRating, Long totalReviews) {
        this.influencerId = influencerId;
        this.name = name;
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }
}
