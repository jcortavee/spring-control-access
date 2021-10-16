package com.umg.accesscontrol.repositories;

import com.umg.accesscontrol.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
