package com.example.marketing.Repostiroy;

import com.example.marketing.Model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findTypeById(Integer id);
}
