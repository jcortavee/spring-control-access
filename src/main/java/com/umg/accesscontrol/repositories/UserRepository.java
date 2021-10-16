package com.umg.accesscontrol.repositories;

import com.umg.accesscontrol.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getCustomerByUsername(String username);
}
