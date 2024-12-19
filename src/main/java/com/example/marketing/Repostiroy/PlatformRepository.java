package com.example.marketing.Repostiroy;

import com.example.marketing.Model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
///   Essa

public interface PlatformRepository extends JpaRepository<Platform, Integer> {
    Platform findPlatformById(Integer id);
}