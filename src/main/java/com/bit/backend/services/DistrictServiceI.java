package com.bit.backend.services;

import com.bit.backend.dtos.DistrictDto;

import java.util.List;

public interface DistrictServiceI {
    DistrictDto addDistrict(DistrictDto districtDto);
    List<DistrictDto> getAllDistricts();
    DistrictDto getDistrictById(long id);
    DistrictDto updateDistrict(long id, DistrictDto districtDto);
    DistrictDto deleteDistrict(long id);
}
