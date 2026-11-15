package com.bit.backend.services;

import com.bit.backend.dtos.SectionDto;

import java.util.List;

public interface SectionServiceI {
    SectionDto addSection(SectionDto sectionDto);
    List<SectionDto> getAllSections();
    SectionDto getSectionById(long id);
    SectionDto updateSection(long id, SectionDto sectionDto);
    SectionDto deleteSection(long id);
}
