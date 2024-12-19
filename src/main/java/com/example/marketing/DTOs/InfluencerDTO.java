package com.example.marketing.DTOs;

import com.example.marketing.Model.BookingOneTime;
import com.example.marketing.Model.Platform;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class InfluencerDTO {

    private String influencer_name;

    private String influencer_phone;

    private String influencer_email;

    private List<PlatformDTO> platforms;

    private List<BookingOneTime> bookingOneTimes;

    public InfluencerDTO(String influencer_name, String influencer_phone, String influencer_email, List<PlatformDTO> platforms) {
        this.influencer_name = influencer_name;
        this.influencer_phone = influencer_phone;
        this.influencer_email = influencer_email;
        this.platforms = platforms;
    }

    // Constructor مع الحقل "bookings"
    public InfluencerDTO(String influencer_name, String influencer_phone, String influencer_email, List<PlatformDTO> platforms, List<BookingOneTime> bookingOneTimes) {
        this.influencer_name = influencer_name;
        this.influencer_phone = influencer_phone;
        this.influencer_email = influencer_email;
        this.platforms = platforms;
        this.bookingOneTimes = bookingOneTimes;
    }

//    public InfluencerDTO(@NotEmpty(message = "Influencer name cannot be Empty") @Size(min = 2, max = 100, message = "Influencer name must be between 2 and 100 characters") String influencerName, @NotEmpty(message = "Influencer phone cannot be Empty") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be a valid format (e.g., +966234567890)") String influencerPhone, @NotEmpty(message = "Influencer email cannot be Empty") @Email String influencerEmail, Set<Platform> platforms) {
//
//    }
//
//    public InfluencerDTO(@NotEmpty(message = "Influencer name cannot be Empty") @Size(min = 2, max = 100, message = "Influencer name must be between 2 and 100 characters") String influencerName, @NotEmpty(message = "Influencer phone cannot be Empty") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be a valid format (e.g., +966234567890)") String influencerPhone, @NotEmpty(message = "Influencer email cannot be Empty") @Email String influencerEmail, Set<Platform> platforms, Set<BookingOneTime> bookingOneTimes) {
//    }
}