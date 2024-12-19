package com.example.marketing.Controller;


import com.example.marketing.Model.BookingOneTime;
import com.example.marketing.Service.BookingOneTimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking-one-time")
@RequiredArgsConstructor

public class BookingOneTimeController {

    private final BookingOneTimeService booking_OneTimeService;

    @GetMapping("/getAll")
    public ResponseEntity getAllBookings(){
        return ResponseEntity.ok().body(booking_OneTimeService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addBooking(@RequestBody @Valid BookingOneTime booking_OneTime){
        booking_OneTimeService.addBookingOneTime(booking_OneTime);
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
}
