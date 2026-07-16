package com.attendance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    private String email;

    private String password;

    private String rollNumber;

    private String fullName;

    private String department;

    private Integer year;

    private String section;
}