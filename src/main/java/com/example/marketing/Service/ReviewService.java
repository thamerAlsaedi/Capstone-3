package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.ReviewDTO;
import com.example.marketing.Model.*;
import com.example.marketing.Repostiroy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
//ARWA
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final InfluencerRepository influencerRepository;
    private final BookingOneTimeRepository bookingOneTimeRepository;
    private final BookingPackageRepository bookingPackageRepository;


    ///Essa
    public void addReviewOneTime( Integer company_Id, Integer bookingOneTimeId ,Review review) {
        Company company = companyRepository.findCompanyById(company_Id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        BookingOneTime bookingOneTime1 =bookingOneTimeRepository.findBooking_OneTimeById(bookingOneTimeId);
        if (bookingOneTime1 == null) {
            throw new ApiException("booking One time not found");
        }
        review.setBookingOneTime(bookingOneTime1);
        review.setCompany(company);
        reviewRepository.save(review);
    }










    //1
    public void addReview(Review review, Integer company_Id, Integer bookingPackageId) {
        Company company = companyRepository.findCompanyById(company_Id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        BookingPackage bookingPackage = bookingPackageRepository.findBookingPackageById(bookingPackageId);
        if (bookingPackage == null) {
            throw new ApiException("bookingPackage not found");
        }
        review.setBookingPackage(bookingPackage);
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

    //1
    public List<ReviewDTO> getReviewsByBookingPackage(Integer bookingPackageId) {
        List<Review> reviews = reviewRepository.findByBookingPackageId(bookingPackageId);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDTO reviewDTO = new ReviewDTO(review.getReview_description(), review.getReview_rating());
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    //2
    public List<ReviewDTO> getReviewsByBookingOneTime(Integer bookingOneTimeId) {
        List<Review> reviews = reviewRepository.findByBookingOneTimeId(bookingOneTimeId);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDTO reviewDTO = new ReviewDTO(review.getReview_description(), review.getReview_rating());
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    //3
    public List<ReviewDTO> getReviewsByCompany(Integer companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDTO reviewDTO = new ReviewDTO(review.getReview_description(), review.getReview_rating());
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    //4
    public List<ReviewDTO> getReviewsByMinimumRating(Integer companyId, Integer minimumRating) {
        // Fetch the company by ID
        Company company = companyRepository.findCompanyById(companyId);
        if (company == null) {
            throw new ApiException("Company not found");
        }

        // Fetch reviews for the company
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        List<ReviewDTO> filteredReviews = new ArrayList<>();

        // Filter reviews by the minimum rating
        for (Review review : reviews) {
            if (review.getReview_rating() >= minimumRating) {
                ReviewDTO reviewDTO = new ReviewDTO(review.getReview_description(), review.getReview_rating());
                filteredReviews.add(reviewDTO);
            }
        }
        return filteredReviews;
    }



}

