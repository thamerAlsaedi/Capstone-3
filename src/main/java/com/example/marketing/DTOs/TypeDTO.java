package com.example.marketing.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeDTO {

    private String type_name;

    private Double type_price;
}