package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

public class DepartmentDto {
    private Long id;
    private String deptCode;
    private String deptName;
    private String description;
    //private EmployeeDto headEmployee;
    private StatusDto status;

    public DepartmentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public EmployeeDto getEmployee() {
        return headEmployee;
    }

    public void setEmployee(EmployeeDto headEmployee) {
        this.headEmployee = headEmployee;
    }*/

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    /**
     * Frontend sometimes sends status as id ("1") and sometimes as { id, name }.
     */
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
}
