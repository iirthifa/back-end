package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class GnDivisionDto {
    private Long gnd_serial_id;
    private String gnDivision;
    private String gnCode;
    private String mpaCode;
    private Long divSectSerialId;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public GnDivisionDto() {
    }
}
