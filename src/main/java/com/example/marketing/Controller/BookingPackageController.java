package com.example.marketing.Controller;

import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.DTOs.BookingPackageDTO;
import com.example.marketing.DTOs.ReviewDTO;
import com.example.marketing.Model.BookingPackage;
import com.example.marketing.Service.BookingPackageService;
import com.example.marketing.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking-package")
//ARWA
public class BookingPackageController   {

    private final BookingPackageService bookingPackageService;

    @PostMapping("/add/{influencer_id}/{company_id}/{packages_Id}")
    public ResponseEntity addBookingPackage(@PathVariable Integer influencer_id, @PathVariable Integer company_id,@PathVariable Integer packages_Id , @RequestBody @Valid BookingPackage bookingPackage) {
        bookingPackageService.addBookingPackage(influencer_id, company_id, packages_Id,bookingPackage);
        return ResponseEntity.status(200).body(new ApiResponse("Booking package added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBookingPackage(@PathVariable Integer id, @RequestBody @Valid BookingPackage bookingPackage) {
        bookingPackageService.updateBookingPackage(id, bookingPackage);
        return ResponseEntity.status(200).body(new ApiResponse("BookingPackage updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookingPackage(@PathVariable Integer id) {
        bookingPackageService.deleteBookingPackage(id);
        return ResponseEntity.status(200).body(new ApiResponse("BookingPackage deleted successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getBookingPackage() {
        return ResponseEntity.status(200).body(bookingPackageService.getBookingPackage());
    }
    //3
    @GetMapping("/booking-packages/{influencerId}")
    public ResponseEntity getAllBookingPackagesByInfluencer(@PathVariable Integer influencerId) {
            List<BookingPackageDTO> bookingPackages = bookingPackageService.getAllBookingPackagesByInfluencerId(influencerId);
        return ResponseEntity.status(200).body(bookingPackages);
    }
    //4
    @GetMapping("/booking-packages/{companyId}/{status}")
    public ResponseEntity getBookingPackagesByStatus(@PathVariable Integer companyId, @PathVariable String status) {
        List<BookingPackageDTO> bookingPackages = bookingPackageService.getBookingPackagesByStatus(companyId, status);
        return ResponseEntity.status(200).body(bookingPackages);
    }
    //6
    @PutMapping("/update-status/{id}/{newStatus}")
    public ResponseEntity updateBookingStatus(
            @PathVariable Integer id,
            @PathVariable String newStatus) {
        bookingPackageService.updateBookingStatus(id, newStatus);
        return ResponseEntity.status(200).body(new ApiResponse("Booking status updated successfully"));
    }

    //7
    @GetMapping("/status/{id}")
    public ResponseEntity getBookingPackageStatus(@PathVariable("id") Integer id) {
        String status = bookingPackageService.getBookingPackageStatus(id);
        return ResponseEntity.status(200).body(status);
    }

    //8
    @PutMapping("/cancelPending")
    public List<BookingPackageDTO> cancelPendingBookingPackages() {
        return bookingPackageService.cancelPendingBookingPackages();
    }

    //9
    @GetMapping("/api/v1/booking-package/booking-packages/company/{companyId}")
    public ResponseEntity getAllBookingPackagesByCompany(@PathVariable Integer companyId) {
        List<BookingPackageDTO> bookingPackages = bookingPackageService.getAllBookingPackagesByCompanyId(companyId);
        return ResponseEntity.status(200).body(bookingPackages);
    }

    //10
    @GetMapping("/influencer/{influencerId}/revenue")
    public ResponseEntity getTotalRevenueForInfluencer(@PathVariable Integer influencerId) {
        double totalRevenue = bookingPackageService.calculateTotalRevenueForInfluencer(influencerId);
        return ResponseEntity.status(200).body(totalRevenue);
    }





}
