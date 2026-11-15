package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class PollingDevisionDto {
    private Long polling_serial_id;
    private String pollingDevisionName;
    private String electoralDistrict;
    private Integer distanceFromCom;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public PollingDevisionDto() {
    }
}
