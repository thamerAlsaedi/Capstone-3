package com.example.marketing.Service;

import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.DTOs.PackageDTO;
import com.example.marketing.Model.Package;
import com.example.marketing.Model.Influencer;
import com.example.marketing.Repostiroy.InfluencerRepository;
import com.example.marketing.Repostiroy.PackageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;

    private final InfluencerRepository influencerRepository;


    public List<PackageDTO> getAllPackageByInfluencerId(Integer influencerId) {
        List<Package> packages = packageRepository.findPackagesByInfluencerId(influencerId);
        if (packages.isEmpty()) {
            throw new RuntimeException("No packages found with id " + influencerId);
        }

        List<PackageDTO> packageDTOS = new ArrayList<>();
        for (Package p : packages) {

            PackageDTO packageDTO = new PackageDTO(p.getId(),p.getPackage_times(),p.getPackage_price(),p.getPlatform_name(),p.isAvailable());
            packageDTOS.add(packageDTO);
        }
        return packageDTOS;
    }

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Package getPackageById(Integer id) {
        return packageRepository.findById(id).orElseThrow(()-> new ApiException("Package not found"));
    }

    public void addPackage(Integer id ,Package pack) {
        Influencer influencer = influencerRepository.findInfluencerById(id);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        pack.setInfluencer(influencer);
        influencerRepository.save(influencer);
        packageRepository.save(pack);

    }


    public void updatePackage(Integer influencerId ,Integer packId, Package pack) {


        Influencer influencer = influencerRepository.findInfluencerById(influencerId);
        if (influencer == null) {
            throw new ApiException("Influencer not found");
        }

        Package oldPackage = packageRepository.findPackageByIdAndInfluencerId(packId,influencerId);
        if (oldPackage == null) {
            throw new ApiException("Package not found");
        }

        oldPackage.setPackage_price(pack.getPackage_price());
        oldPackage.setPackage_times(pack.getPackage_times());
        oldPackage.setAvailable(pack.isAvailable());
        oldPackage.setPlatform_name(pack.getPlatform_name());
        oldPackage.setInfluencer(influencer);

        influencerRepository.save(influencer);
        packageRepository.save(oldPackage);
    }




    public void deletePackage(Integer id) {
        Package pack = packageRepository.findById(id).orElseThrow(()-> new ApiException("Package not found"));
        packageRepository.delete(pack);
    }


    public void updateStatusPackage(Package pack) {

        Influencer influencer = influencerRepository.findInfluencerById(pack.getId());
        if (influencer == null) {
            throw new ApiException("Influencer not found for package ID: " + pack.getId());
        }


        Package existingPackage = packageRepository.findById(pack.getId())
                .orElseThrow(() -> new ApiException("Package not found with ID: " + pack.getId()));


        existingPackage.setAvailable(pack.isAvailable());


        packageRepository.save(existingPackage);
    }

    public List<Package> getPackagesByPriceRange(Double lower, Double higher) {
        if (lower == null || higher == null || lower > higher) {
            throw new ApiException("Invalid price range");
        }
        return packageRepository.findPackageByPackage_priceIsBetween(lower, higher);
    }

    public List<Package> getPackagesByAvailability(boolean available) {
        return packageRepository.findPackageByAvailable(available);
    }

    public List<Package> findPackageByPriceIsLessThan(double price) {
        return packageRepository.findPackageByPackage_priceIsLessThan(price);
    }


    public List<Package> findPackageByPriceIsGreaterThan(Double higher) {
        return packageRepository.findPackageByPackage_priceIsGreaterThan(higher);
    }

}
