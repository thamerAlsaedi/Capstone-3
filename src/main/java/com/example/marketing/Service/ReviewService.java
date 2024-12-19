package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.ReviewDTO;
import com.example.marketing.Model.Company;
import com.example.marketing.Model.Review;
import com.example.marketing.Repostiroy.BookingOneTimeRepository;
import com.example.marketing.Repostiroy.BookingPackageRepository;
import com.example.marketing.Repostiroy.CompanyRepository;
import com.example.marketing.Repostiroy.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final BookingOneTimeRepository bookingOneTimeRepository;
    private final BookingPackageRepository bookingPackageRepository;

    //1
    public void addReview(Review review , Integer company_Id) {
        Company company = companyRepository.findCompanyById(company_Id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        review.setCompany(company);
        reviewRepository.save(review);
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDTO reviewDTO = new ReviewDTO(review.getReview_description(), review.getReview_rating());
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    public void updateReview(Integer id, Review review) {
        Review existingReview = reviewRepository.findReviewById(id);

        if (existingReview == null) {
            throw new ApiException("Review not found");
        }

        existingReview.setReview_description(review.getReview_description());
        existingReview.setReview_rating(review.getReview_rating());
        reviewRepository.save(existingReview);
    }

    public void deleteReview(Integer id) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new ApiException("Review not found");
        }
        reviewRepository.delete(review);
    }
}
