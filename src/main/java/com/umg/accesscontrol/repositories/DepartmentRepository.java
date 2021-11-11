package com.umg.accesscontrol.repositories;

import com.umg.accesscontrol.models.Department;
import com.umg.accesscontrol.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
