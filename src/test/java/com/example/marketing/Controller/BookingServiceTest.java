package com.example.marketing.Controller;

import com.example.marketing.Model.BookingOneTime;
import com.example.marketing.Repostiroy.BookingOneTimeRepository;
import com.example.marketing.Service.BookingOneTimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Service
@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingOneTimeService bookingOneTimeService; //الخدمة التي تحتوي على changeStatusByDate

    @Mock
    private BookingOneTimeRepository bookingOneTimeRepository; 

    @Test
    public void testChangeStatusByDate() {

        BookingOneTime booking1 = new BookingOneTime();
        booking1.setBookingDate(LocalDateTime.now().withSecond(30));
        booking1.setBookingStatus("PENDING");

        BookingOneTime booking2 = new BookingOneTime();
        booking2.setBookingDate(LocalDateTime.now());
        booking2.setBookingStatus("PENDING");

        List<BookingOneTime> mockBookings = Arrays.asList(booking1, booking2);


        Mockito.when(bookingOneTimeRepository.findBookingOneTimeByBookingStatus("PENDING"))
                .thenReturn(mockBookings);


        bookingOneTimeService.changeStatusByDate();


        Assertions.assertEquals("REJECT", booking1.getBookingStatus());

        Assertions.assertEquals("PENDING", booking2.getBookingStatus());


        Mockito.verify(bookingOneTimeRepository, Mockito.times(1)).save(booking1);
        Mockito.verify(bookingOneTimeRepository, Mockito.never()).save(booking2);
    }
}

