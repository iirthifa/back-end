package com.bit.backend.services;

import com.bit.backend.dtos.GnDivisionDto;

import java.util.List;

public interface GnDivisionServiceI {
    GnDivisionDto addGnDivision(GnDivisionDto gnDivisionDto);
    List<GnDivisionDto> getAllGnDivisions();
    GnDivisionDto getGnDivisionById(long id);
    GnDivisionDto updateGnDivision(long id, GnDivisionDto gnDivisionDto);
    GnDivisionDto deleteGnDivision(long id);
}
