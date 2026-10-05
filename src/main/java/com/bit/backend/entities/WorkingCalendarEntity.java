package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "working_calendar")
public class WorkingCalendarEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek;

    @Column(name = "is_working_day", nullable = false)
    private Boolean isWorkingDay;

    public WorkingCalendarEntity() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getIsWorkingDay() { return isWorkingDay; }

    public void setIsWorkingDay(Boolean isWorkingDay) {
        this.isWorkingDay = isWorkingDay;
    }

}
