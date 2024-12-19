package com.example.marketing.Controller;

import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.Model.Review;
import com.example.marketing.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/review")

public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/add/{company_Id}")
    public ResponseEntity addReview(@PathVariable Integer company_Id, @RequestBody @Valid Review review){
        reviewService.addReview(review, company_Id);
        return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReview(@PathVariable Integer id, @RequestBody @Valid Review review){
        reviewService.updateReview(id, review);
        return ResponseEntity.status(200).body(new ApiResponse("Review updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReview(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.status(200).body(new ApiResponse("Review deleted successfully"));
    }
}
