package com.example.marketing.Controller;

import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.DTOs.CompanyDTO;
import com.example.marketing.Model.Company;
import com.example.marketing.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

     // hussam
    @Autowired
    private CompanyService companyService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCompany(@RequestBody @Valid Company company) {
        companyService.addCompany(company);
        return ResponseEntity.status(200).body(new ApiResponse("successfully added Company"));
    }


    @GetMapping("/get")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getCompaniesDTos());
    }


    @GetMapping("/findByCompany_id/{Company_id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Integer Company_id) {
        return ResponseEntity.ok(companyService.getCompanyById(Company_id));
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCompany(@RequestBody @Valid Company company) {
        companyService.updateCompany(company);
        return ResponseEntity.status(200).body(new ApiResponse("successfully updated Company"));
    }


    @DeleteMapping("/delete/{Company_id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer Company_id) {
        companyService.deleteCompany(Company_id);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted Company"));
    }


    // hussam
    @GetMapping("/send")
    public ResponseEntity<ApiResponse> sendMail1(
            @RequestParam Integer company_id,
            @RequestParam Integer influencerId,
            @RequestParam String subject,
            @RequestParam String body) {

        companyService.sendEmail(company_id, influencerId, subject, body);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully sent email"));
    }

}

