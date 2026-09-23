package com.bit.backend.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rosterEntry")
public class RosterEntryEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_date", nullable = false)
    private Date workDate;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shift_id", nullable = false)
    private ShiftEntity shift;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roster_period_id", nullable = false)
    private RosterPeriodEntity rosterPeriod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;

    public RosterEntryEntity() {
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

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public ShiftEntity getShift() {
        return shift;
    }

    public void setShift(ShiftEntity shift) {
        this.shift = shift;
    }

    public RosterPeriodEntity getRosterPeriod() {
        return rosterPeriod;
    }

    public void setRosterPeriod(RosterPeriodEntity rosterPeriod) {
        this.rosterPeriod = rosterPeriod;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
