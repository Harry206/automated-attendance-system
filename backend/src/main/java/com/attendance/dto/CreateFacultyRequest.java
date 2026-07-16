package com.attendance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFacultyRequest {

    private String email;

    private String password;

    private String employeeNumber;

    private String fullName;

    private String department;
}