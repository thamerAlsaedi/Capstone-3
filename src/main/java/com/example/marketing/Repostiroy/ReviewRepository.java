package com.example.marketing.Repostiroy;

import com.example.marketing.DTOs.InfluencerRatingDTO;
import com.example.marketing.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//ARWA
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findReviewById(Integer id);

    List<Review> findByBookingPackageId(Integer bookingPackageId);

    List<Review> findByBookingOneTimeId(Integer bookingOneTimeId);

    List<Review> findByCompanyId(Integer companyId);


    //Essa almutiri
    @Query("""
           SELECT r.bookingOneTime.influencer.id,
                  r.bookingOneTime.influencer.influencer_name,
                  AVG(r.review_rating),
                  COUNT(r.id)
           FROM Review r
           GROUP BY r.bookingOneTime.influencer.id, r.bookingOneTime.influencer.influencer_name
           ORDER BY AVG(r.review_rating) DESC
           """)
    List<Object[]> findTopInfluencersRaw();



}
