package com.bit.backend.services;

import com.bit.backend.dtos.NationalityDto;

import java.util.List;

public interface NationalityServiceI {
    NationalityDto addNationality(NationalityDto nationalityDto);
    List<NationalityDto> getAllNationalitys();
    NationalityDto getNationalityById(long id);
    NationalityDto updateNationality(long id, NationalityDto nationalityDto);
    NationalityDto deleteNationality(long id);
}
