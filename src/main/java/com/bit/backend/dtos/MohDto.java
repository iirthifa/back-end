package com.bit.backend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class MohDto {
    private Long moh_serial_id;
    private String mohName;
    private String contactNumber;
    private Long districtSerialId;
    private Boolean active;
    private Boolean isDeleted;
    private Long deletedBy;
    private LocalDateTime deletedDate;

    public MohDto() {
    }
}
