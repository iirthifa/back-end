package com.bit.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "divisional_secretariat")
@Getter
@Setter

public class DivisionalSecretariatEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dev_sect_serial_id;

    @Column(name = "divisional_secretariat_name", length = 45)
    private String divisionalSecretariatName;

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
