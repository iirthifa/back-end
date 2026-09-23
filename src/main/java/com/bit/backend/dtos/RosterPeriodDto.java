package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;

public class RosterPeriodDto {
    private Long id;
    private String periodName;
    private Date startDate;
    private Date endDate;
    private Boolean published;
    private StatusDto status;

    public RosterPeriodDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

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
