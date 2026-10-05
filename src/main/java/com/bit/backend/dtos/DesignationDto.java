package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

public class DesignationDto {
    private Long id;
    private String designationName;
    private Integer gradeLevel;
    private String description;

    public DesignationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public Integer getGradeLevel() { return gradeLevel; }

    public void setGradeLevel(Integer gradeLevel) { this.gradeLevel = gradeLevel; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
