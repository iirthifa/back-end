package com.bit.backend.services;

import com.bit.backend.dtos.DesignationDto;

import java.util.List;

public interface DesignationServiceI {
    DesignationDto addDesignation(DesignationDto designationDto);
    List<DesignationDto> getAllDesignations();
    DesignationDto getDesignationById(long id);
    DesignationDto updateDesignation(long id, DesignationDto designationDto);
    DesignationDto deleteDesignation(long id);
}
