package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class SectionDto {
    private Long sect_serial_id;
    private String sectName;
    private Long deptSerialId;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public SectionDto() {
    }
}
