package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class PoliceStationDto {
    private Long police_station_serial_id;
    private String policeStationName;
    private String policeDivision;
    private String phone;
    private Long provinceId;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public PoliceStationDto() {
    }
}
