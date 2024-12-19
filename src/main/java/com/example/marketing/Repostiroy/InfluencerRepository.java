package com.example.marketing.Repostiroy;

import com.example.marketing.Model.Influencer;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer,Integer> {
    Influencer findInfluencerById(Integer id);

    List<Influencer> findInfluencerBySpecialty(@Param("specialty") String specialty);


}