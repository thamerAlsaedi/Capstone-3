package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.*;
import com.example.marketing.Model.BookingOneTime;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Model.Platform;
import com.example.marketing.Model.Type;
import com.example.marketing.Repostiroy.InfluencerRepository;
import com.example.marketing.Repostiroy.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
///   Essa

public class InfluencerService {

    private final InfluencerRepository influencerRepository;
    private final ReviewRepository reviewRepository;


    public List<InfluencerDTO> getAllInfluencer() {
        List<Influencer> influencers = influencerRepository.findAll();
        List<InfluencerDTO> DTO = new ArrayList<>();
        for (Influencer influencer : influencers) {

            List<PlatformDTO> platforms = new ArrayList<>();
            for (Platform platform : influencer.getPlatforms()) {
                List<TypeDTO> types = new ArrayList<>();
                for (Type type : platform.getTypes()) {

                    List<BookingOneTimeDTO> bookingOneTimeDTOs = new ArrayList<>();
                    for (BookingOneTime bookingOneTime : type.getBookingOneTimes()) {
                        BookingOneTimeDTO bookingOneTimeDTO = new BookingOneTimeDTO();
                        bookingOneTimeDTO.setBooking_date(bookingOneTime.getBookingDate());
                        bookingOneTimeDTO.setBooking_status(bookingOneTime.getBookingStatus());
                        bookingOneTimeDTO.setAdvertisementStartDate(bookingOneTime.getAdvertisementStartDate());
                        bookingOneTimeDTO.setId(bookingOneTime.getId());
                        bookingOneTimeDTOs.add(bookingOneTimeDTO);
                    }

                    TypeDTO typeDTO = new TypeDTO(type.getType_name(), type.getType_price(),bookingOneTimeDTOs);
                    types.add(typeDTO);
                }
                PlatformDTO platformDTO = new PlatformDTO(platform.getPlatform_name(), platform.getPlatform_followers(), types);
                platforms.add(platformDTO);
            }

            InfluencerDTO d = new InfluencerDTO(influencer.getInfluencer_name(), influencer.getInfluencer_phone(), influencer.getInfluencer_email(), platforms);

            DTO.add(d);
        }

        return DTO;
    }

    public void add(Influencer influencer) {
        influencerRepository.save(influencer);
    }

    public void update(Integer id, Influencer influencer) {
        Influencer influencer1 = influencerRepository.findInfluencerById(id);
        if (influencer1 == null) {
            throw new ApiException("influencer not found");
        }
        influencer1.setInfluencer_name(influencer.getInfluencer_name());
        influencer1.setInfluencer_email(influencer.getInfluencer_email());
        influencer1.setInfluencer_phone(influencer.getInfluencer_phone());
        influencer1.setSpecialty(influencer.getSpecialty());
        influencerRepository.save(influencer1);
    }

    public void delete(Integer id) {
        Influencer influencer1 = influencerRepository.findInfluencerById(id);
        if (influencer1 == null) {
            throw new ApiException("influencer not found");
        }
        influencerRepository.delete(influencer1);
    }

    public List findBySpecialty(String specialty) {
        List<Influencer> list = influencerRepository.findInfluencersBySpecialty(specialty);
        if (list == null) {
            throw new ApiException("influencer not found");
        }
        return list;
    }

    public List topPerformers() {

        return reviewRepository.findTopInfluencersRaw();

    }
}












