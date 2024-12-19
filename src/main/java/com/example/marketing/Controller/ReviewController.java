package com.example.marketing.Controller;

import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.DTOs.ReviewDTO;
import com.example.marketing.Model.Review;
import com.example.marketing.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/review")
//ARWA
public class ReviewController {

    private final ReviewService reviewService;
    @PostMapping("/addReviewOneTime/{company_Id}/{bookingOneTimeId}")
    public ResponseEntity addReviewOneTime(@PathVariable Integer company_Id,@PathVariable Integer bookingOneTimeId ,@RequestBody@Valid Review review){
        reviewService.addReviewOneTime(company_Id, bookingOneTimeId, review);
        return ResponseEntity.ok().body("added review one time successfully");
    }

    @GetMapping("/get")
    public ResponseEntity getReviews(){
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }

    @PostMapping("/add/{company_Id}/{bookingPackageId}")
    public ResponseEntity addReview(@PathVariable Integer company_Id,@PathVariable Integer bookingPackageId, @RequestBody @Valid Review review){
        reviewService.addReview(review, company_Id,bookingPackageId);
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
    //1
    @GetMapping("/bookingPackage/{bookingPackageId}")
    public ResponseEntity getReviewsByBookingPackage(
            @PathVariable("bookingPackageId") Integer bookingPackageId) {
        List<ReviewDTO> reviewDTOS = reviewService.getReviewsByBookingPackage(bookingPackageId);
        return ResponseEntity.status(200).body(reviewDTOS);
    }
    //2
    @GetMapping("/bookingOneTimePackage/{bookingOneTimeId}")
    public ResponseEntity getReviewsByBookingOneTime(@PathVariable Integer bookingOneTimeId) {
        List<ReviewDTO> reviewDTOS = reviewService.getReviewsByBookingOneTime(bookingOneTimeId);
        return ResponseEntity.status(200).body(reviewDTOS);
    }

    //3
    @GetMapping("/company/{companyId}")
    public ResponseEntity getReviewsByCompany(
            @PathVariable("companyId") Integer companyId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByCompany(companyId);
        return ResponseEntity.status(200).body(reviews);
    }
    //4
    @GetMapping("/range/{companyId}/{minimumRating}")
    public ResponseEntity getReviewsByRatingRange(@PathVariable Integer companyId, @PathVariable Integer minimumRating){
        return ResponseEntity.status(200).body(reviewService.getReviewsByMinimumRating( companyId, minimumRating));
    }



}
