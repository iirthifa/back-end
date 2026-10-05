package com.bit.backend.dtos;

public class WorkingCalendarDto {
    private Long id;
    private Integer dayOfWeek;
    private Boolean isWorkingDay;

    public WorkingCalendarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getIsWorkingDay() {
        return isWorkingDay;
    }

    public void setIsWorkingDay(Boolean isWorkingDay) {
        this.isWorkingDay = isWorkingDay;
    }

}
