package com.example.marketing.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PackageDTO {



    private Integer id;
    private int package_times;
    private Double package_price;
    private String platform_name;
    private boolean isAvailable;

}
