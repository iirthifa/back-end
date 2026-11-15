package com.bit.backend.services;

import com.bit.backend.dtos.ProvinceDto;

import java.util.List;

public interface ProvinceServiceI {
    ProvinceDto addProvince(ProvinceDto provinceDto);
    List<ProvinceDto> getAllProvinces();
    ProvinceDto getProvinceById(long id);
    ProvinceDto updateProvince(long id, ProvinceDto provinceDto);
    ProvinceDto deleteProvince(long id);
}
