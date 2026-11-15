package com.bit.backend.services;

import com.bit.backend.dtos.DivisionalSecretariatDto;

import java.util.List;

public interface DivisionalSecretariatServiceI {
    DivisionalSecretariatDto addDivisionalSecretariat(DivisionalSecretariatDto divisionalSecretariatDto);
    List<DivisionalSecretariatDto> getAllDivisionalSecretariats();
    DivisionalSecretariatDto getDivisionalSecretariatById(long id);
    DivisionalSecretariatDto updateDivisionalSecretariat(long id, DivisionalSecretariatDto divisionalSecretariatDto);
    DivisionalSecretariatDto deleteDivisionalSecretariat(long id);
}
