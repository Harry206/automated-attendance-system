package com.attendance.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;

    private String rollNumber;

    private String fullName;

    private String email;

    private String department;

    private Integer year;

    private String section;
}