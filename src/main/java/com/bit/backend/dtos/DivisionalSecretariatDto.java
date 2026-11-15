package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class DivisionalSecretariatDto {
    private Long dev_sect_serial_id;
    private String divisionalSecretariatName;
    private Long districtSerialId;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public DivisionalSecretariatDto() {
    }
}
