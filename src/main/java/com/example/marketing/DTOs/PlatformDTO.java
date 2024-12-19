package com.example.marketing.DTOs;

import com.example.marketing.Model.Influencer;
import com.example.marketing.Model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class PlatformDTO {

    private String platform_name;

    private Double platform_followers;



    private Influencer influencer;


    private List<Type> types;


}
