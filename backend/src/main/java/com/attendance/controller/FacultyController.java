package com.attendance.controller;

import com.attendance.dto.CreateFacultyRequest;
import com.attendance.dto.FacultyResponse;
import com.attendance.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public FacultyResponse createFaculty(@Valid @RequestBody CreateFacultyRequest request) {
        return facultyService.createFaculty(request);
    }
}