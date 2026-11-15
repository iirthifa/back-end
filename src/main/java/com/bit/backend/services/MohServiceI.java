package com.bit.backend.services;

import com.bit.backend.dtos.MohDto;

import java.util.List;

public interface MohServiceI {
    MohDto addMoh(MohDto mohDto);
    List<MohDto> getAllMohs();
    MohDto getMohById(long id);
    MohDto updateMoh(long id, MohDto mohDto);
    MohDto deleteMoh(long id);
}
