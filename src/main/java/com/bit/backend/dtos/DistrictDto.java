package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class DistrictDto {
    private Long district_serial_id;
    private String districtName;
    private Long provinceSerialId;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public DistrictDto() {
    }
}
