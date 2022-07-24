package com.laioffer.staybooking.repository;

import com.laioffer.staybooking.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//concrete this interface to object use spring data frame
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findAuthorityByUsername(String username);
}
