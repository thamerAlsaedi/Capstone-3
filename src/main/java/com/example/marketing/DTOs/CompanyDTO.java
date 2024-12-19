package com.example.marketing.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDTO {


    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyCR;


}
