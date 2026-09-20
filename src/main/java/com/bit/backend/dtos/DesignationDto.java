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

    /**
     * Frontend sometimes sends status as id ("1") and sometimes as { id, name }.

    @com.fasterxml.jackson.annotation.JsonSetter("status")
    public void setStatusFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.status = null;
            return;
        }
        StatusDto dto = new StatusDto();
        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.status = dto;
            return;
        }
        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            if (node.hasNonNull("name")) {
                dto.setName(node.get("name").asText());
            }
            this.status = dto;
        }
    }
     */
}
