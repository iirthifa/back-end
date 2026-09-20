package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class DepartmentEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "dept_name", nullable = false)
    private String deptName;

    @Column(name = "description")
    private String description;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "head_employee_id", nullable = false)
    //private EmployeeEntity headEmployee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;

    public DepartmentEntity() {
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

    //public EmployeeEntity getHeadEmployee() { return headEmployee; }

    //public void setHeadEmployee(EmployeeEntity headEmployee) { this.headEmployee = headEmployee; }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}