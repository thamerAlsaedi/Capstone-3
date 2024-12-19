package com.example.marketing.Repostiroy;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.marketing.Model.Package;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    Package findPackageById(Integer id);

    @Query("SELECT p FROM Package p WHERE p.influencer.id = :influencerId and p.isAvailable=true")
    List<Package> findPackagesByInfluencerId(@Param("influencerId") Integer influencerId);

    Package findPackageByIdAndInfluencerId(Integer packId, Integer influencerId);

     @Query("SELECT p FROM Package p WHERE p.package_price between :lower AND  :higher")
    List<Package> findPackageByPackage_priceIsBetween(@Param("lower") Double lower, @Param("higher") Double higher);


    @Query("SELECT p FROM Package p WHERE p.package_price <=  :lower")
     List<Package>findPackageByPackage_priceIsLessThan(@Param("lower") Double lower);

    @Query("SELECT p FROM Package p WHERE p.package_price >=  :higher")
     List<Package>findPackageByPackage_priceIsGreaterThan(@Param("higher") Double higher);

    @Query("SELECT p FROM Package p WHERE p.isAvailable= :available")
    List<Package> findPackageByAvailable(@Param("available") boolean available);



}
