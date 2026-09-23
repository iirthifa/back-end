package com.bit.backend.services;

import com.bit.backend.dtos.RosterPeriodDto;

import java.util.List;

public interface RosterPeriodServiceI {
    RosterPeriodDto addRosterPeriod(RosterPeriodDto rosterPeriodDto);
    List<RosterPeriodDto> getAllRosterPeriods();
    RosterPeriodDto getRosterPeriodById(long id);
    RosterPeriodDto updateRosterPeriod(long id, RosterPeriodDto rosterPeriodDto);
    RosterPeriodDto deleteRosterPeriod(long id);
}
