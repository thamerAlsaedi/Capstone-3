package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.Model.BookingOneTime;
import com.example.marketing.Model.Company;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Repostiroy.BookingOneTimeRepository;
import com.example.marketing.Repostiroy.CompanyRepository;
import com.example.marketing.Repostiroy.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class BookingOneTimeService {
    private final BookingOneTimeRepository bookingOneTimeRepository;
    private final InfluencerRepository influencerRepository;
    private final CompanyRepository companyRepository;

    private final EmailSenderJava emailSender;

    public List getAll(){
        return bookingOneTimeRepository.findAll();
    }

    public void addBookingOneTime(BookingOneTime booking_OneTime){
        Influencer influencer = influencerRepository.findInfluencerById(booking_OneTime.getInfluencer().getId());
        if(influencer == null){
            throw new ApiException("influencer not found");
        }

        Company company = companyRepository.findCompanyById(booking_OneTime.getCompany().getId());
        if(company == null){
            throw new ApiException("company not found");
        }

        booking_OneTime.setInfluencer(influencer);
        booking_OneTime.setCompany(company);
        companyRepository.save(company);
        influencerRepository.save(influencer);
        bookingOneTimeRepository.save(booking_OneTime);

        // hussam
        emailSender.sendEmail(company.getCompany_email(),
                "add Booking One Time  successful",
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + company.getCompany_name() + ",</p><br>" +
                        "<p>Successfully added Booking One Time  with Your Id " + company.getId() + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

    }

    public void updateBookingOneTime(Integer id , BookingOneTime booking_OneTime){
        BookingOneTime b = bookingOneTimeRepository.findBooking_OneTimeById(id);
        if (b == null){
            throw new ApiException("booking OneTime not found");
        }
        b.setBooking_date(booking_OneTime.getBooking_date());
        b.setBooking_status(booking_OneTime.getBooking_status());
        b.setBooking_totalPrice(booking_OneTime.getBooking_totalPrice());
        bookingOneTimeRepository.save(b);
    }

    public void deleteBookingOneTime(Integer id){
        BookingOneTime b = bookingOneTimeRepository.findBooking_OneTimeById(id);
        if (b == null){
            throw new ApiException("booking OneTime not found");
        }
        bookingOneTimeRepository.delete(b);
    }
}