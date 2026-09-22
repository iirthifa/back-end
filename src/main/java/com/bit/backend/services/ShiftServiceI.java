package com.bit.backend.services;

import com.bit.backend.dtos.ShiftDto;

import java.util.List;

public interface ShiftServiceI {
    ShiftDto addShift(ShiftDto shiftDto);
    List<ShiftDto> getAllShifts();
    ShiftDto getShiftById(long id);
    ShiftDto updateShift(long id, ShiftDto shiftDto);
    ShiftDto deleteShift(long id);
}
