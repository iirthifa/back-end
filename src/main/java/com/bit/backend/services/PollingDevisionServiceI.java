package com.bit.backend.services;

import com.bit.backend.dtos.PollingDevisionDto;

import java.util.List;

public interface PollingDevisionServiceI {
    PollingDevisionDto addPollingDevision(PollingDevisionDto pollingDevisionDto);
    List<PollingDevisionDto> getAllPollingDevisions();
    PollingDevisionDto getPollingDevisionById(long id);
    PollingDevisionDto updatePollingDevision(long id, PollingDevisionDto pollingDevisionDto);
    PollingDevisionDto deletePollingDevision(long id);
}
