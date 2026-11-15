package com.bit.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "moh")
@Getter
@Setter

public class MohEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moh_serial_id;

    @Column(name = "moh_name", length = 45)
    private String mohName;

    @Column(name = "contact_number", length = 45)
    private String contactNumber;

    @Column(name = "district_serial_id")
    private Long districtSerialId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
