package com.attendance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyResponse {

    private Long id;

    private String employeeNumber;

    private String fullName;

    private String email;

    private String department;
}