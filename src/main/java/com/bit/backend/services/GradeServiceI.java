package com.bit.backend.services;

import com.bit.backend.dtos.GradeDto;

import java.util.List;

public interface GradeServiceI {
    GradeDto addGrade(GradeDto gradeDto);
    List<GradeDto> getAllGrades();
    GradeDto getGradeById(long id);
    GradeDto updateGrade(long id, GradeDto gradeDto);
    GradeDto deleteGrade(long id);
}
