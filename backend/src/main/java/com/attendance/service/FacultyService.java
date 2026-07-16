package com.attendance.service;

import com.attendance.dto.CreateFacultyRequest;
import com.attendance.dto.FacultyResponse;
import com.attendance.entity.Faculty;
import com.attendance.entity.User;
import com.attendance.enums.Role;
import com.attendance.repository.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UserService userService;

    public FacultyService(FacultyRepository facultyRepository,
                          UserService userService) {
        this.facultyRepository = facultyRepository;
        this.userService = userService;
    }

    public FacultyResponse createFaculty(CreateFacultyRequest request) {

        if (facultyRepository.existsByEmployeeNumber(request.getEmployeeNumber())) {
            throw new RuntimeException("Employee number already exists");
        }

        User savedUser = userService.createUser(
                request.getEmail(),
                request.getPassword(),
                Role.FACULTY
        );

        Faculty faculty = Faculty.builder()
                .employeeNumber(request.getEmployeeNumber())
                .fullName(request.getFullName())
                .department(request.getDepartment())
                .user(savedUser)
                .build();

        Faculty savedFaculty = facultyRepository.save(faculty);

        return FacultyResponse.builder()
                .id(savedFaculty.getId())
                .employeeNumber(savedFaculty.getEmployeeNumber())
                .fullName(savedFaculty.getFullName())
                .email(savedFaculty.getUser().getEmail())
                .department(savedFaculty.getDepartment())
                .build();
    }
}