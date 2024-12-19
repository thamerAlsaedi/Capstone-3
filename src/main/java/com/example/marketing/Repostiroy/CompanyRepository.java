package com.example.marketing.Repostiroy;

import com.example.marketing.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

   Company findCompanyById(Integer company_id);

}
