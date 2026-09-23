package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;

public class RosterEntryDto {
    private Long id;
    private Date workDate;
    private String notes;
    private EmployeeDto employee;
    private ShiftDto shift;
    private RosterPeriodDto rosterPeriod;
    private StatusDto status;

    public RosterEntryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public EmployeeDto getEmployee() { return employee; }

    public void setEmployee(EmployeeDto employee) { this.employee = employee; }

    public ShiftDto getShift() { return shift; }

    public void setShift(ShiftDto shift) { this.shift = shift; }

    public RosterPeriodDto getRosterPeriod() { return rosterPeriod; }

    public void setRosterPeriod(RosterPeriodDto rosterPeriod) { this.rosterPeriod = rosterPeriod; }

    public StatusDto getStatus() { return status; }

    public void setStatus(StatusDto status) { this.status = status; }

    /**
     * Frontend sometimes sends employee as id ("1") and sometimes as { id, firstName, lastName }.
     */
    @com.fasterxml.jackson.annotation.JsonSetter("employee")
    public void setEmployeeFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.employee = null;
            return;
        }
        EmployeeDto dto = new EmployeeDto();
        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.employee = dto;
            return;
        }
        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            if (node.hasNonNull("firstName")) {
                dto.setFirstName(node.get("firstName").asText());
            }
            if (node.hasNonNull("lastName")) {
                dto.setLastName(node.get("lastName").asText());
            }
            this.employee = dto;
        }
    }

    /**
     * Frontend sometimes sends shift as id ("1") and sometimes as { id, name }.
     */
    @com.fasterxml.jackson.annotation.JsonSetter("shift")
    public void setShiftFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.shift = null;
            return;
        }
        ShiftDto dto = new ShiftDto();
        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.shift = dto;
            return;
        }
        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            if (node.hasNonNull("shiftName")) {
                dto.setShiftName(node.get("shiftName").asText());
            }
            this.shift = dto;
        }
    }

    /**
     * Frontend sometimes sends status as id ("1") and sometimes as { id, name }.
     */
    @com.fasterxml.jackson.annotation.JsonSetter("rosterPeriod")
    public void setRosterPeriodFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.rosterPeriod = null;
            return;
        }
        RosterPeriodDto dto = new RosterPeriodDto();
        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.rosterPeriod = dto;
            return;
        }
        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            if (node.hasNonNull("periodName")) {
                dto.setPeriodName(node.get("periodName").asText());
            }
            this.rosterPeriod = dto;
        }
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
