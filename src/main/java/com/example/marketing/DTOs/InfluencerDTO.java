package com.example.marketing.DTOs;

import com.example.marketing.Model.Platform;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfluencerDTO {

    private String Influencer_name;

    private String Influencer_phone;

    private String Influencer_email;

    private List<Platform> platforms;



}