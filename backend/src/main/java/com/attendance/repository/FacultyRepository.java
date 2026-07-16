package com.attendance.repository;

import com.attendance.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByEmployeeNumber(String employeeNumber);

}