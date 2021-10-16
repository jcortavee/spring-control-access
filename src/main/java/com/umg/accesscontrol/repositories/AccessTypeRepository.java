package com.umg.accesscontrol.repositories;

import com.umg.accesscontrol.models.AccessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTypeRepository extends JpaRepository<AccessType, Long> {
}
