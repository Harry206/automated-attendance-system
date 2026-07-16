package com.attendance.service;

import com.attendance.dto.CreateStudentRequest;
import com.attendance.dto.StudentResponse;
import com.attendance.entity.Student;
import com.attendance.entity.User;
import com.attendance.enums.Role;
import com.attendance.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;

    public StudentService(StudentRepository studentRepository,
                          UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    public StudentResponse createStudent(CreateStudentRequest request) {

        if (studentRepository.existsByRollNumber(request.getRollNumber())) {
            throw new RuntimeException("Roll number already exists");
        }

        User savedUser = userService.createUser(
                request.getEmail(),
                request.getPassword(),
                Role.STUDENT
        );

        Student student = Student.builder()
                .rollNumber(request.getRollNumber())
                .fullName(request.getFullName())
                .department(request.getDepartment())
                .year(request.getYear())
                .section(request.getSection())
                .faceRegistered(false)
                .user(savedUser)
                .build();

        Student savedStudent = studentRepository.save(student);

        return StudentResponse.builder()
                .id(savedStudent.getId())
                .rollNumber(savedStudent.getRollNumber())
                .fullName(savedStudent.getFullName())
                .email(savedStudent.getUser().getEmail())
                .department(savedStudent.getDepartment())
                .year(savedStudent.getYear())
                .section(savedStudent.getSection())
                .build();
    }
}