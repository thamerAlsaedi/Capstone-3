package com.example.marketing.Controller;


import com.example.marketing.Model.Influencer;
import com.example.marketing.Service.InfluencerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/influencer")

public class InfluencerController {

    private final InfluencerService influencerService;
    @GetMapping("/getAll")
    public ResponseEntity getAllInfluencers(){
        return ResponseEntity.ok().body(influencerService.getAllInfluencer());
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

    @GetMapping("/topPerformers")
    public ResponseEntity topPerformers(){
        List list = influencerService.topPerformers();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/findBySpecialty/{specialty}")
    public ResponseEntity findBySpecialty(@PathVariable String specialty){
        List list = influencerService.findBySpecialty(specialty);
        return ResponseEntity.ok().body(list);
    }

}