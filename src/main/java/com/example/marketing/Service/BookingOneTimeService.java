package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.BookingOneTimeDTO;
import com.example.marketing.Model.*;
import com.example.marketing.Repostiroy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
///   Essa


public class BookingOneTimeService {
    private final BookingOneTimeRepository bookingOneTimeRepository;
    private final InfluencerRepository influencerRepository;
    private final CompanyRepository companyRepository;
    private final TypeRepository typeRepository;
    private final PlatformRepository platformRepository;




    public List getAll(){
        return bookingOneTimeRepository.findAll();
    }

    public void addBookingOneTime(Integer companyId , Integer influenerId ,Integer platformId , Integer typeId , BookingOneTime booking_OneTime){
        Influencer influencer = influencerRepository.findInfluencerById(influenerId);
        if(influencer == null){
            throw new ApiException("influencer not found");
        }

        Company company = companyRepository.findCompanyById(companyId);
        if(company == null){
            throw new ApiException("company not found");
        }
        Platform platform = platformRepository.findPlatformById(platformId);
        if(platform == null){
            throw new ApiException("platform not found");
        }

        Type type = typeRepository.findTypeById(typeId);
        if(type == null){
            throw new ApiException("type not found");
        }


        booking_OneTime.setPlatform(platform);
        booking_OneTime.setType(type);
        booking_OneTime.setInfluencer(influencer);
        booking_OneTime.setCompany(company);
        companyRepository.save(company);
        influencerRepository.save(influencer);
        bookingOneTimeRepository.save(booking_OneTime);

    }

    public void updateBookingOneTime(Integer id , BookingOneTime booking_OneTime){
        BookingOneTime b = bookingOneTimeRepository.findBooking_OneTimeById(id);
        if (b == null){
            throw new ApiException("booking OneTime not found");
        }
        b.setBookingDate(booking_OneTime.getBookingDate());
        b.setBookingStatus(booking_OneTime.getBookingStatus());
//        b.setBookingTotalPrice(booking_OneTime.getBookingTotalPrice());
        b.setPlatform(booking_OneTime.getPlatform());
        b.setType(booking_OneTime.getType());
        b.setInfluencer(booking_OneTime.getInfluencer());
        b.setCompany(booking_OneTime.getCompany());

        bookingOneTimeRepository.save(b);
    }

    public void deleteBookingOneTime(Integer id){
        BookingOneTime b = bookingOneTimeRepository.findBooking_OneTimeById(id);
        if (b == null){
            throw new ApiException("booking OneTime not found");
        }
        bookingOneTimeRepository.delete(b);
    }


    public void changeBookingStatus(Integer id , String status){
        BookingOneTime b = bookingOneTimeRepository.findBooking_OneTimeById(id);
        if (b == null){
            throw new ApiException("booking OneTime not found");
        }

        Influencer influencer = influencerRepository.findInfluencerById(b.getInfluencer().getId());
        if(influencer == null){
            throw new ApiException("influencer not found");
        }

        b.setBookingStatus(status);
        bookingOneTimeRepository.save(b);
    }
    public BookingOneTime getBookingOneTimeDTO(Integer id){
        BookingOneTime b = bookingOneTimeRepository.findBooking_OneTimeById(id);
        if(b == null){
            throw new ApiException("booking OneTime not found");
        }
        return b;
    }


    public void changeStatusByDate(){
    List<BookingOneTime> list = bookingOneTimeRepository.findBookingOneTimeByBookingStatus("PENDING");

        for (BookingOneTime bookingOneTime : list) {
            if(bookingOneTime.getBookingDate().isBefore(LocalDateTime.now().minusSeconds(30))) {
                bookingOneTime.setBookingStatus("REJECT");
                bookingOneTimeRepository.save(bookingOneTime);
            }
        }
    }

    @Scheduled(cron = "0,30 * * * * ?")
    public void scheduleTaskStatusUpdate() {
        changeStatusByDate();
    }


    public List<BookingOneTimeDTO> findBookingOneTimeByInfluencerIdAndBookingStatus(Integer influencerId, String bookingStatus){
        List<BookingOneTime> bookingOneTimes = bookingOneTimeRepository.findBookingOneTimeByInfluencerIdAndBookingStatus(influencerId, bookingStatus);
        if(bookingOneTimes == null){
            throw new ApiException("booking OneTime not found");
        }
        List<BookingOneTimeDTO> bookingOneTimeDTOS = new ArrayList<>();

     for (BookingOneTime bookingOneTime : bookingOneTimes) {
         BookingOneTimeDTO b = new BookingOneTimeDTO();

         b.setBooking_status(bookingOneTime.getBookingStatus());
//         b.setBooking_totalPrice(bookingOneTime.getBookingTotalPrice());
         b.setBooking_date(bookingOneTime.getBookingDate());
         bookingOneTimeDTOS.add(b);
     }
     return bookingOneTimeDTOS;
    }

    public List<BookingOneTime> findWorkHistory(Integer companyId, Integer influencerId){
        List<BookingOneTime> list = bookingOneTimeRepository.findWorkHistory(companyId, influencerId);
            if(list == null){
                throw new ApiException("work history not found");
            }

        return list;

    }

    public void acceptBooking(Integer bookingId) {
        BookingOneTime booking = bookingOneTimeRepository.findBooking_OneTimeById(bookingId);
                if(booking == null){
                    throw new ApiException("booking not found");
                }

        if (!"PENDING".equals(booking.getBookingStatus())) {
            throw new ApiException("Booking is not in Pending state");
        }

        booking.setBookingStatus("Accepted");
        boolean isScheduled = scheduleCampaign(booking);

        if (isScheduled ==false) {
            booking.setBookingStatus("In Progress"); // تغيير الحالة إلى قيد التنفيذ
        } else {
            throw new ApiException("Unable to schedule the booking due to conflicts");
        }

         bookingOneTimeRepository.save(booking);
    }


    private Boolean scheduleCampaign(BookingOneTime bookingOneTime){
        LocalDate startDate = bookingOneTime.getAdvertisementStartDate();
        // التحقق من وجود حجوزات متداخلة
        List<BookingOneTime> conflictingBookings = bookingOneTimeRepository.findConflictingBookings(
                bookingOneTime.getCompany().getId(), bookingOneTime.getInfluencer().getId(), startDate);

        // إذا لم توجد تعارضات، يتم جدولة الحملة
        return conflictingBookings.isEmpty();
    }









}
