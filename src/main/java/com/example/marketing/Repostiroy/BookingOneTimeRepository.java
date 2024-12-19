package com.example.marketing.Repostiroy;

import com.example.marketing.Model.BookingOneTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingOneTimeRepository extends JpaRepository<BookingOneTime, Integer> {

    BookingOneTime  findBooking_OneTimeById(Integer booking_id);
}
