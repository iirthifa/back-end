package com.bit.backend.services;

import com.bit.backend.dtos.PoliceStationDto;

import java.util.List;

public interface PoliceStationServiceI {
    PoliceStationDto addPoliceStation(PoliceStationDto policeStationDto);
    List<PoliceStationDto> getAllPoliceStations();
    PoliceStationDto getPoliceStationById(long id);
    PoliceStationDto updatePoliceStation(long id, PoliceStationDto policeStationDto);
    PoliceStationDto deletePoliceStation(long id);
}
