package com.example.marketing.Controller;


import com.example.marketing.Model.BookingOneTime;
import com.example.marketing.Service.BookingOneTimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking-one-time")
@RequiredArgsConstructor

public class BookingOneTimeController {

    private final BookingOneTimeService booking_OneTimeService;

    @GetMapping("/getAll")
    public ResponseEntity getAllBookings(){
        return ResponseEntity.ok().body(booking_OneTimeService.getAll());
    }
    @PostMapping("/add/{companyId}/{influencerId}/{platformId}/{typeId}")
    public ResponseEntity addBooking(@PathVariable Integer companyId ,@PathVariable Integer influencerId ,@PathVariable Integer platformId ,@PathVariable Integer typeId , @RequestBody @Valid BookingOneTime booking_OneTime){
        booking_OneTimeService.addBookingOneTime(companyId,influencerId, platformId ,  typeId ,booking_OneTime);
        return ResponseEntity.ok().body("add booking success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBooking(@PathVariable Integer id , @RequestBody@Valid BookingOneTime booking_OneTime){
        booking_OneTimeService.updateBookingOneTime(id,booking_OneTime);
        return ResponseEntity.ok().body("update booking success");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBooking(@PathVariable Integer id ){
        booking_OneTimeService.deleteBookingOneTime(id);
        return ResponseEntity.ok().body("delete booking success");
    }

    @PutMapping("/changeBookingStatus/{id}/{status}")
    public ResponseEntity changeBookingStatus(@PathVariable Integer id ,@PathVariable String status){
        booking_OneTimeService.changeBookingStatus(id,status);
        return ResponseEntity.ok().body("change booking success");
    }

    @PutMapping("/getBookingOneTimeDTO/{id}")
    public ResponseEntity getBookingOneTimeDTO(@PathVariable Integer id){
        return ResponseEntity.ok().body(booking_OneTimeService.getBookingOneTimeDTO(id));
    }

    @PutMapping("/changeStatusByDate")
    public ResponseEntity changeStatusByDate(){
        booking_OneTimeService.changeStatusByDate();
        return ResponseEntity.ok().body("change booking date success");
    }

    @GetMapping("/findBookingOneTimeByInfluencerIdAndBookingStatus/{influencerId}/{bookingStatus}")
    public ResponseEntity findBookingOneTimeByInfluencerIdAndBookingStatus(@PathVariable Integer influencerId,@PathVariable String bookingStatus){
        List list = booking_OneTimeService.findBookingOneTimeByInfluencerIdAndBookingStatus(influencerId,bookingStatus);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/findWorkHistory/{companyId}/{influencerId}")
    public ResponseEntity findWorkHistory(@PathVariable Integer companyId,@PathVariable Integer influencerId){
        List list = booking_OneTimeService.findWorkHistory(companyId,influencerId);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/acceptBooking/{bookingId}")
    public ResponseEntity acceptBooking(@PathVariable Integer bookingId ){
        booking_OneTimeService.acceptBooking(bookingId);
        return ResponseEntity.ok().body("accept booking success");
    }




}
