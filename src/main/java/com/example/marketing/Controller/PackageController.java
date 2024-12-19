package com.example.marketing.Controller;


import com.example.marketing.ApiResponse.ApiException;
import com.example.marketing.ApiResponse.ApiResponse;
import com.example.marketing.DTOs.PackageDTO;
import com.example.marketing.Model.Package;
import com.example.marketing.Service.PackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/package")
@RequiredArgsConstructor
public class PackageController {

    @Autowired
    private final PackageService packageService;
    @Autowired
    private PathMatcher pathMatcher;



    // Endpoint to update Status Package # Hussam Alsaedi
    @PutMapping("/updateStatusPackage")
    public ResponseEntity<ApiResponse> updateStatusPackage(@RequestBody Package pack) {
        packageService.updateStatusPackage(pack);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated status package"));
    }

    // Endpoint to get getAllPackageByInfluencer_id  # Hussam Alsaedi
    @GetMapping("/getAllPackageByInfluencer_id/{influencerId}")
    public ResponseEntity<List<PackageDTO>> getAllPackageByInfluencerId(@PathVariable Integer influencerId) {
        return ResponseEntity.ok(packageService.getAllPackageByInfluencerId(influencerId));
    }


    @GetMapping("/get")
    public ResponseEntity<List<Package>> getAllPackages() {
        return ResponseEntity.ok(packageService.getAllPackages());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable int id) {

        return ResponseEntity.ok(packageService.getPackageById(id));

    }

    @PostMapping("/add/{id}")
    public ResponseEntity<ApiResponse> addPackage(@PathVariable Integer id ,@RequestBody @Valid Package pack) {
        packageService.addPackage(id ,pack);
        return ResponseEntity.status(200).body(new ApiResponse("successfully added Package"));
    }

    @PutMapping("/update-package/{influencerId}/{packId}")
    public ResponseEntity<ApiResponse> updatePackage(@PathVariable Integer influencerId ,@PathVariable Integer packId, @RequestBody @Valid  Package pack) {

        packageService.updatePackage(influencerId,packId,pack);
        return ResponseEntity.status(200).body(new ApiResponse("Package updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletePackage(@PathVariable Integer id) {
        packageService.deletePackage(id);
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted Package"));
    }
     // hussam
    @GetMapping("/price-range/{lower}/{higher}")
    public ResponseEntity<List<Package>> getPackagesByPriceRange(@PathVariable Double lower, @PathVariable Double higher) {
        List<Package> packages = packageService.getPackagesByPriceRange(lower, higher);
        return ResponseEntity.ok(packages);
    }

  // hussam
    @GetMapping("/available/{available}")
    public List<Package> getPackagesByAvailability(@PathVariable boolean available) {
        return packageService.getPackagesByAvailability(available);
    }
    // hussam
    @GetMapping("/findByPriceLessThan/{price}")
    public List<Package> findPackageByPriceIsLessThan(@PathVariable double price) {
        return packageService.findPackageByPriceIsLessThan(price);
    }

    @GetMapping("/findByPriceGreaterThan/{higher}")
    public List<Package> findPackageByPriceIsGreaterThan(@PathVariable Double higher) {
        return packageService.findPackageByPriceIsGreaterThan(higher);
    }
}
