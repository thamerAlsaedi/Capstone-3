package com.example.marketing.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TypeDTO {

    private String type_name;
    private Double type_price;

    private List<BookingOneTimeDTO> bookings;
}