package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.BookingPackageDTO;
import com.example.marketing.DTOs.InfluencerBookingCountDTO;
import com.example.marketing.Model.BookingPackage;
import com.example.marketing.Model.Company;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Model.Package;
import com.example.marketing.Repostiroy.BookingPackageRepository;
import com.example.marketing.Repostiroy.CompanyRepository;
import com.example.marketing.Repostiroy.InfluencerRepository;
import com.example.marketing.Repostiroy.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingPackageService {

    private final BookingPackageRepository bookingPackageRepository;
    private final InfluencerRepository influencerRepository;
    private final CompanyRepository companyRepository;
    private final PackageRepository packageRepository;

    private final EmailSenderJava emailSender;

    public void addBookingPackage(Integer influencer_id, Integer company_id, Integer packages_Id, BookingPackage bookingPackage) {

        Company company = companyRepository.findCompanyById(company_id);
        if (company == null) {
            throw new ApiException("Company not found");
        }
        Influencer influencer = influencerRepository.findInfluencerById(influencer_id);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }
        Package package1 = packageRepository.findPackageById(packages_Id);
        if (package1 == null) {
            throw new ApiException("package not found");
        }

        bookingPackage.setBookingTotalPrice(package1.getPackage_price() * package1.getPackage_times());
        bookingPackage.setCompany(company);
        bookingPackage.setPackages(package1);
        bookingPackage.setInfluencer(influencer);

        companyRepository.save(company);
        influencerRepository.save(influencer);
        packageRepository.save(package1);
        bookingPackageRepository.save(bookingPackage);


        // hussam
        emailSender.sendEmail(company.getCompany_email(),
                "add booking package successful",
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + company.getCompany_name() + ",</p><br>" +
                        "<p>Successfully added package with Your Id " + company.getId() + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");
    }


    public void updateBookingPackage(Integer id, BookingPackage bookingPackage) {
        BookingPackage existingBookingPackage = bookingPackageRepository.findBookingPackageById(id);
        if (existingBookingPackage == null) {
            throw new ApiException("BookingPackage not found");
        }

        existingBookingPackage.setBookingDate(bookingPackage.getBookingDate());
        existingBookingPackage.setBookingTotalPrice(bookingPackage.getBookingTotalPrice());
        existingBookingPackage.setBookingStatus(bookingPackage.getBookingStatus());
        existingBookingPackage.setCompany(bookingPackage.getCompany());
        existingBookingPackage.setInfluencer(bookingPackage.getInfluencer());
        existingBookingPackage.setPackages(bookingPackage.getPackages());

        bookingPackageRepository.save(existingBookingPackage);
    }

    public void deleteBookingPackage(Integer id) {
        BookingPackage bookingPackage = bookingPackageRepository.findById(id).orElse(null);
        if (bookingPackage == null) {
            throw new ApiException("BookingPackage not found");
        }
        bookingPackageRepository.delete(bookingPackage);
    }

    public List<BookingPackageDTO> getBookingPackage() {
        List<BookingPackage> bookingPackages = bookingPackageRepository.findAll();
        List<BookingPackageDTO> bookingPackageDTOS = new ArrayList<>();
        for (BookingPackage bookingPackage : bookingPackages) {
            BookingPackageDTO bookingPackageDTO = new BookingPackageDTO(bookingPackage.getId(),
                    bookingPackage.getBookingDate(),
                    bookingPackage.getBookingTotalPrice(),
                    bookingPackage.getBookingStatus(),
                    bookingPackage.getCompany().getId(),
                    bookingPackage.getInfluencer().getId(),
                    bookingPackage.getPackages().getId());
            bookingPackageDTOS.add(bookingPackageDTO);
        }
        return bookingPackageDTOS;
    }


    //5 fetching all booking packages for a specific influencer. This could be useful for an influencer to track their collaborations.
    public List<BookingPackageDTO> getAllBookingPackagesByInfluencerId(Integer influencerId) {
        Influencer influencer = influencerRepository.findInfluencerById(influencerId);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        List<BookingPackage> bookingPackages = bookingPackageRepository.findAllByInfluencerId(influencerId);
        if (bookingPackages.isEmpty()) {
            throw new ApiException("No booking packages found for this influencer");
        }

        List<BookingPackageDTO> bookingPackageDTOS = new ArrayList<>();
        for (BookingPackage bookingPackage : bookingPackages) {
            bookingPackageDTOS.add(new BookingPackageDTO(
                    bookingPackage.getId(),
                    bookingPackage.getBookingDate(),
                    bookingPackage.getBookingTotalPrice(),
                    bookingPackage.getBookingStatus(),
                    bookingPackage.getCompany().getId(),
                    bookingPackage.getInfluencer().getId(),
                    bookingPackage.getPackages().getId()));
        }

        return bookingPackageDTOS;
    }

    //6
    public List<BookingPackageDTO> getBookingPackagesByStatus(Integer companyId, String status) {
        Company company = companyRepository.findCompanyById(companyId);
        if (company == null) {
            throw new ApiException("Company not found");
        }

        List<BookingPackage> bookingPackages = bookingPackageRepository.findAllByCompanyIdAndBookingStatus(companyId, status);
        if (bookingPackages.isEmpty()) {
            throw new ApiException("No booking packages found with the given status");
        }

        List<BookingPackageDTO> bookingPackageDTOS = new ArrayList<>();
        for (BookingPackage bookingPackage : bookingPackages) {
            bookingPackageDTOS.add(new BookingPackageDTO(
                    bookingPackage.getId(),
                    bookingPackage.getBookingDate(),
                    bookingPackage.getBookingTotalPrice(),
                    bookingPackage.getBookingStatus(),
                    bookingPackage.getCompany().getId(),
                    bookingPackage.getInfluencer().getId(),
                    bookingPackage.getPackages().getId()));
        }

        return bookingPackageDTOS;
    }

    //7
    public void updateBookingStatus(Integer id, String newStatus) {
        BookingPackage bookingPackage = bookingPackageRepository.findBookingPackageById(id);
        if (bookingPackage == null) {
            throw new ApiException("Booking package not found");
        }
        if (newStatus == null || newStatus.isEmpty()) {
            throw new ApiException("Status cannot be null or empty");
        }

        bookingPackage.setBookingStatus(newStatus);
        bookingPackageRepository.save(bookingPackage);
    }

    public String getBookingPackageStatus(Integer id) {
        BookingPackage bookingPackage = bookingPackageRepository.findBookingPackageById(id);
        if (bookingPackage == null) {
            throw new ApiException("BookingPackage not found");
        }
        return bookingPackage.getBookingStatus();  // Return the booking status
    }

    //8
    public List<BookingPackageDTO> cancelPendingBookingPackages() {
        LocalDateTime twoDaysAgo = LocalDateTime.now().minus(2, ChronoUnit.DAYS);


        List<BookingPackage> bookingPackages = bookingPackageRepository.findAllByBookingStatus("PENDING");
        List<BookingPackageDTO> canceledBookingPackages = new ArrayList<>();

        for (BookingPackage bookingPackage : bookingPackages) {

            if (bookingPackage.getBookingDate().isBefore(twoDaysAgo)) {
                bookingPackage.setBookingStatus("CANCELED");

                bookingPackageRepository.save(bookingPackage);
                canceledBookingPackages.add(new BookingPackageDTO(
                        bookingPackage.getId(),
                        bookingPackage.getBookingDate(),
                        bookingPackage.getBookingTotalPrice(),
                        bookingPackage.getBookingStatus(),
                        bookingPackage.getCompany().getId(),
                        bookingPackage.getInfluencer().getId(),
                        bookingPackage.getPackages().getId()));
                
            }
        }

        return canceledBookingPackages;
    }


    //9
    public List<BookingPackageDTO> getAllBookingPackagesByCompanyId(Integer companyId) {
        Company company = companyRepository.findCompanyById(companyId);
        if (company == null) {
            throw new ApiException("Company not found");
        }

        List<BookingPackage> bookingPackages = bookingPackageRepository.findBookingPackageByCompanyId(companyId);
        if (bookingPackages.isEmpty()) {
            throw new ApiException("No booking packages found for this company");
        }

        List<BookingPackageDTO> bookingPackageDTOS = new ArrayList<>();
        for (BookingPackage bookingPackage : bookingPackages) {
            bookingPackageDTOS.add(new BookingPackageDTO(
                    bookingPackage.getId(),
                    bookingPackage.getBookingDate(),
                    bookingPackage.getBookingTotalPrice(),
                    bookingPackage.getBookingStatus(),
                    bookingPackage.getCompany().getId(),
                    bookingPackage.getInfluencer().getId(),
                    bookingPackage.getPackages().getId()));
        }

        return bookingPackageDTOS;
    }

    //10
    public double calculateTotalRevenueForInfluencer(Integer influencerId) {
        Influencer influencer = influencerRepository.findInfluencerById(influencerId);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        // Fetch all completed bookings for the influencer
        List<BookingPackage> completedBookings = bookingPackageRepository.findAllByInfluencerIdAndBookingStatus(influencerId, "COMPLETED");
        if (completedBookings.isEmpty()) {
            throw new ApiException("No completed bookings found for this influencer");
        }

        // Calculate the total revenue
        double totalRevenue = 0;
        for (BookingPackage bookingPackage : completedBookings) {
            totalRevenue += bookingPackage.getBookingTotalPrice();
        }

        return totalRevenue;
    }
    public String getMaxInfluencerName() {
        List<InfluencerBookingCountDTO> result = bookingPackageRepository.findMaxInfluencerBookings();

        if (!result.isEmpty()) {
            InfluencerBookingCountDTO maxInfluencer = result.get(0); // Get the top influencer
            return maxInfluencer.getInfluencerName(); // Return the influencer's name
        }
        return "No influencer found";
    }

}




