package com.bit.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "section")
@Getter
@Setter
public class SectionEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sect_serial_id;

    @Column(name = "sect_name", length = 45)
    private String sectName;

    @Column(name = "dept_serial_id")
    private Long deptSerialId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
