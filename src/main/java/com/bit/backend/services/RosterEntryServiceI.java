package com.bit.backend.services;

import com.bit.backend.dtos.RosterEntryDto;

import java.util.List;

public interface RosterEntryServiceI {
    RosterEntryDto addRosterEntry(RosterEntryDto rosterEntryDto);
    List<RosterEntryDto> getAllRosterEntries();
    RosterEntryDto getRosterEntryById(long id);
    RosterEntryDto updateRosterEntry(long id, RosterEntryDto rosterEntryDto);
    RosterEntryDto deleteRosterEntry(long id);
}
