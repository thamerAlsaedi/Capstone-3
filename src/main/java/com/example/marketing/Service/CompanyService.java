package com.example.marketing.Service;


import com.example.marketing.DTOs.CompanyDTO;
import com.example.marketing.Model.Company;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Model.Notification;
import com.example.marketing.Repostiroy.CompanyRepository;
import com.example.marketing.Repostiroy.InfluencerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private final EmailSenderJava emailSender;
    @Autowired
    private final NotificationService notificationService;
    private final InfluencerRepository influencerRepository;


    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    public CompanyDTO getCompanyById(Integer Company_id) {

        Company com = companyRepository.findCompanyById(Company_id);
        if (com == null) {
            throw new RuntimeException("Company not found");
        }

        CompanyDTO companyDTO = new CompanyDTO(com.getCompany_name(), com.getCompany_email(), com.getCompany_phone(), com.getCompany_CR());

        return companyDTO;
    }


    public void addCompany(Company company) {
        companyRepository.save(company);

        emailSender.sendEmail(company.getCompany_email(),
                "Welcome to Our platform",
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + company.getCompany_name() + ",</p><br>" +
                        "<p>Successfully Created account with Your Id " + company.getId() + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        Notification notification = new Notification();
        notification.setNotification_ToUser(company.getCompany_email());
        notification.setNotification_description("create new account with Your Id " + company.getId());

        notificationService.addNotification(notification);

    }


    public void updateCompany(Company updatedCompany) {

        Company existingCompany = companyRepository.findCompanyById(updatedCompany.getId());
        if (existingCompany == null) {
            throw new RuntimeException("Company not found with id: " + updatedCompany.getId());
        }

        existingCompany.setCompany_name(updatedCompany.getCompany_name());
        existingCompany.setCompany_email(updatedCompany.getCompany_email());
        existingCompany.setCompany_phone(updatedCompany.getCompany_phone());
        existingCompany.setCompany_CR(updatedCompany.getCompany_CR());

        companyRepository.save(existingCompany);


        emailSender.sendEmail(updatedCompany.getCompany_email(),
                "updated account ",
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + updatedCompany.getCompany_name() + ",</p><br>" +
                        "<p>Successfully updated account with Your Id " + updatedCompany.getId() + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        Notification notification = new Notification();
        notification.setNotification_ToUser(updatedCompany.getCompany_email());
        notification.setNotification_description("update account with Your Id " + updatedCompany.getId());

        notificationService.addNotification(notification);
    }


    public void deleteCompany(Integer Company_id) {
        Company company = companyRepository.findCompanyById(Company_id);
        if (company == null) {
            throw new RuntimeException("Company not found with id: " + Company_id);
        }
        companyRepository.delete(company);
    }


    public List<CompanyDTO> getCompaniesDTos() {
        List<Company> companies = companyRepository.findAll();

        List<CompanyDTO> companiesDTos = new ArrayList<>();

        for (Company com : companies) {
            CompanyDTO companyDTO = new CompanyDTO(com.getCompany_name(), com.getCompany_email(), com.getCompany_phone(), com.getCompany_CR());
            companiesDTos.add(companyDTO);
        }

        return companiesDTos;
    }


    public void sendEmail(Integer company_id, Integer influencerId,  String subject, String body) {

        Influencer influencer =  influencerRepository.findInfluencerById(influencerId);
        if (influencer == null) {
            throw new RuntimeException("Influencer not found with id: " + influencerId);
        }

        Company company = companyRepository.findCompanyById(company_id);
        if (company == null) {
            throw new RuntimeException("Company not found with id: " + company_id);
        }
        emailSender.sendEmail(influencer.getInfluencer_email(),
                subject,
                "<html>" +
                        "<body style='background-color: green; font-size: 18px; color: white;'>" +
                        "<div style='background-color: white; border: 4px solid green; padding: 10px; color: black;'>" +
                        "<p>Hello Mr. " + influencer.getInfluencer_name() + ",</p><br>" +
                        "<p>" + body + "</p><br>" +
                        "<p>Best regards,</p>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

    }

}


