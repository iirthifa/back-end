package com.bit.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "police_station")
@Getter
@Setter

public class PoliceStationEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long police_station_serial_id;

    @Column(name = "police_station_name", length = 45)
    private String policeStationName;

    @Column(name = "police_division", length = 45)
    private String policeDivision;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
