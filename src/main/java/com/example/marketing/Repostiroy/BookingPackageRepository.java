package com.example.marketing.Repostiroy;

import com.example.marketing.DTOs.InfluencerBookingCountDTO;
import com.example.marketing.Model.BookingPackage;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Model.Package;
import com.example.marketing.DTOs.InfluencerBookingCountDTO;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BookingPackageRepository extends JpaRepository<BookingPackage, Integer> {

    BookingPackage findBookingPackageById(Integer id);

    List<BookingPackage> findAllByInfluencerId(Integer influencerId);
    List<BookingPackage> findBookingPackageByCompanyId(Integer companyId );

    List<BookingPackage> findAllByCompanyIdAndBookingStatus(Integer companyId, String status);

    List<BookingPackage> findAllByBookingStatus(String pending);

    List<BookingPackage> findAllByInfluencerIdAndBookingStatus(Integer influencerId, String completed);

    @Query("SELECT new com.example.marketing.DTOs.InfluencerBookingCountDTO(b.influencer.influencer_name, COUNT(b)) " +
            "FROM BookingPackage b " +
            "GROUP BY b.influencer " +
            "ORDER BY COUNT(b) DESC")
    List<InfluencerBookingCountDTO> findMaxInfluencerBookings();




}
