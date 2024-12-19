package com.example.marketing.Controller;


import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.DTOs.InfluencerDTOGetName;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Service.CompanyService;
import com.example.marketing.Service.InfluencerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/influencer")

public class InfluencerController {



    private final InfluencerService influencerService;
    private final CompanyService companyService;


    @GetMapping("/getInfluencerById/{id}")
    public ResponseEntity<Influencer> getInfluencerById(@PathVariable Integer id) {
        return   ResponseEntity.ok(influencerService.getInfluencerById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllInfluencers(){
        return ResponseEntity.ok().body(influencerService.getAllInfluencer());
    }

    // Endpoint get only influences names and id  By specialty # hussam Alsaedi
    @GetMapping("/get-influences-name-id/{specialty}")
    public ResponseEntity<List<InfluencerDTOGetName>> getInfluencesNameId(@PathVariable String specialty) {
        List<InfluencerDTOGetName> influencerDTOs = influencerService.getAllInfluencerDTO(specialty);
        return ResponseEntity.ok(influencerDTOs);
    }


    @PostMapping("/add")
    public ResponseEntity addInfluencer(@RequestBody @Valid Influencer influencer){
        influencerService.add(influencer);
        return ResponseEntity.ok().body("influencer added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateInfluencer(@PathVariable Integer id , @RequestBody@Valid Influencer influencer){
        influencerService.update(id, influencer);
        return ResponseEntity.ok().body("influencer updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInfluencer(@PathVariable Integer id){
        influencerService.delete(id);
        return ResponseEntity.ok().body("influencer deleted successfully");
    }


    @GetMapping("/send")
    public ResponseEntity<ApiResponse> sendMail1(
            @RequestParam Integer company_id,
            @RequestParam Integer influencerId,
            @RequestParam String subject,
            @RequestParam String body) {

        influencerService.sendEmail( influencerId,company_id, subject, body);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully sent email"));
    }
}