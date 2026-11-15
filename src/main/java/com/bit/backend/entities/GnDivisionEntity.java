package com.bit.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gn_division")
@Getter
@Setter

public class GnDivisionEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gnd_serial_id;

    @Column(name = "gn_division", length = 45)
    private String gnDivision;

    @Column(name = "gn_code", length = 45)
    private String gnCode;

    @Column(name = "mpa_code", length = 45)
    private String mpaCode;

    @Column(name = "div_sect_serial_id")
    private Long divSectSerialId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
