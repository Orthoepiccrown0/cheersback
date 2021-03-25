package com.cheers.main.repository;

import com.cheers.main.model.account.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Optional<Company> findByEmailAndPassword(String email, String password);

    Optional<Company> findByEmail(String email);

}
