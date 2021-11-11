package com.umg.accesscontrol.repositories;

import com.umg.accesscontrol.models.Access;
import com.umg.accesscontrol.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {

    Optional<Access> findFirstByUserOrderByCreatedAtDesc(User user);
    List<Access> findAccessByUser(User user);

}
