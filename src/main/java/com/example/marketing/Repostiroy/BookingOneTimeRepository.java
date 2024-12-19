package com.example.marketing.Repostiroy;

import com.example.marketing.Model.BookingOneTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
///   Essa

public interface BookingOneTimeRepository extends JpaRepository<BookingOneTime, Integer> {

    BookingOneTime  findBooking_OneTimeById(Integer booking_id);


//    List<BookingOneTime> findBookingOneTimeByBookingStatus(String bookingStatus);

    List<BookingOneTime> findBookingOneTimeByBookingStatus(String bookingStatus);


    List<BookingOneTime> findBookingOneTimeByInfluencerIdAndBookingStatus(Integer influencerId, String bookingStatus);

    @Query("""
           SELECT b
           FROM BookingOneTime b
           WHERE b.company.id  = ?1
             AND b.influencer.id = ?2
             AND b.bookingStatus = "COMPLETED"
           ORDER BY b.advertisementStartDate DESC
           """)
    List<BookingOneTime> findWorkHistory( Integer companyId, Integer influencerId);

    @Query("""
           SELECT b
           FROM BookingOneTime b
           WHERE b.company.id = :companyId
             AND b.influencer.id = :influencerId
             AND b.advertisementStartDate = :startDate
           """)
    List<BookingOneTime> findConflictingBookings( Integer companyId, Integer influencerId, LocalDate startDate);
}
