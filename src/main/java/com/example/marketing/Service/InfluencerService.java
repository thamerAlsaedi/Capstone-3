package com.example.marketing.Service;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.InfluencerDTO;
import com.example.marketing.DTOs.InfluencerDTOGetName;
import com.example.marketing.Model.Company;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Repostiroy.CompanyRepository;
import com.example.marketing.Repostiroy.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfluencerService {

    private final InfluencerRepository influencerRepository;
    private final CompanyRepository companyRepository;
    @Autowired
    private final EmailSenderJava emailSender;

    public List<Influencer> getAllInfluencer(){
        return influencerRepository.findAll();
    }


    //  Hussam Alsaedi
    public List<InfluencerDTOGetName> getAllInfluencerDTO(String specialty) {
        List<Influencer> influencers = influencerRepository.findInfluencerBySpecialty(specialty);

        if (influencers.isEmpty()) {
            throw new ApiException("No influencers found with specialty: " + specialty);
        }



        return influencers.stream()
                .map(influencer -> new InfluencerDTOGetName(influencer.getInfluencer_name(), influencer.getId()))
                .collect(Collectors.toList());
    }

    public Influencer getInfluencerById(Integer id) {
        return  influencerRepository.findInfluencerById(id);

    }


    public void add(Influencer influencer){

        influencer.setPlatforms(influencer.getPlatforms());
        influencerRepository.save(influencer);

        emailSender.sendEmail(influencer.getInfluencer_email(),
                "Welcome to Our platform",
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + influencer.getInfluencer_name() + ",</p><br>" +
                        "<p>Successfully Created account with Your Id " + influencer.getId() + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

    }

    public void update(Integer id ,Influencer influencer){
        Influencer influencer1 = influencerRepository.findInfluencerById(id);
        if(influencer1 == null){
            throw new ApiException("influencer not found");
        }
        influencer1.setInfluencer_name(influencer.getInfluencer_name());
        influencer1.setInfluencer_email(influencer.getInfluencer_email());
        influencer1.setInfluencer_phone(influencer.getInfluencer_phone());
        influencerRepository.save(influencer1);
    }

    public void delete(Integer id){
        Influencer influencer1 = influencerRepository.findInfluencerById(id);
        if(influencer1 == null){
            throw new ApiException("influencer not found");
        }
        influencerRepository.delete(influencer1);
    }

    public void sendEmail( Integer influencerId,Integer company_id,  String subject, String body) {

        Influencer influencer =  influencerRepository.findInfluencerById(influencerId);
        if (influencer == null) {
            throw new RuntimeException("Influencer not found with id: " + influencerId);
        }

        Company company = companyRepository.findCompanyById(company_id);
        if (company == null) {
            throw new RuntimeException("Company not found with id: " + company_id);
        }
        emailSender.sendEmail(company.getCompany_email(),
                subject,
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + company.getCompany_name() + ",</p><br>" +
                        "<p>" + body + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

    }

}
