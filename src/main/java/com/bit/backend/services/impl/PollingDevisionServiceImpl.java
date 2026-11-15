package com.bit.backend.services.impl;

import com.bit.backend.dtos.PollingDevisionDto;
import com.bit.backend.entities.PollingDevisionEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.PollingDevisionMapper;
import com.bit.backend.repositories.PollingDevisionRepository;
import com.bit.backend.services.PollingDevisionServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class PollingDevisionServiceImpl implements PollingDevisionServiceI {

    private final PollingDevisionRepository pollingDevisionRepository;
    private final PollingDevisionMapper pollingDevisionMapper;

    public PollingDevisionServiceImpl(PollingDevisionRepository pollingDevisionRepository, PollingDevisionMapper pollingDevisionMapper) {
        this.pollingDevisionRepository = pollingDevisionRepository;
        this.pollingDevisionMapper = pollingDevisionMapper;
    }

    @Override
    @Transactional
    public PollingDevisionDto addPollingDevision(PollingDevisionDto pollingDevisionDto) {
        PollingDevisionEntity entity = pollingDevisionMapper.toPollingDevisionEntity(pollingDevisionDto);

        entity.setPolling_serial_id(null);
        entity.setIsDeleted(Boolean.FALSE);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);

        if (entity.getActive() == null) {
            entity.setActive(Boolean.TRUE);
        }

        return pollingDevisionMapper.toPollingDevisionDto(pollingDevisionRepository.save(entity));
    }

    @Override
    public List<PollingDevisionDto> getAllPollingDevisions() {
        return pollingDevisionMapper.toPollingDevisionDtoList(pollingDevisionRepository.findAll());
    }

    @Override
    public PollingDevisionDto getPollingDevisionById(long id) {
        PollingDevisionEntity entity = pollingDevisionRepository.findById(id)
                .orElseThrow(() -> new AppException("PollingDevision not found", HttpStatus.NOT_FOUND));
        return pollingDevisionMapper.toPollingDevisionDto(entity);
    }

    @Override
    @Transactional
    public PollingDevisionDto updatePollingDevision(long id, PollingDevisionDto pollingDevisionDto) {
        PollingDevisionEntity existing = pollingDevisionRepository.findById(id)
                .orElseThrow(() -> new AppException("PollingDevision not found", HttpStatus.NOT_FOUND));

        setIfPresent(pollingDevisionDto.getPollingDevisionName(), existing::setPollingDevisionName);
        setIfPresent(pollingDevisionDto.getElectoralDistrict(), existing::setElectoralDistrict);
        setIfPresent(pollingDevisionDto.getDistanceFromCom(), existing::setDistanceFromCom);
        setIfPresent(pollingDevisionDto.getActive(), existing::setActive);

        return pollingDevisionMapper.toPollingDevisionDto(pollingDevisionRepository.save(existing));
    }

    @Override
    @Transactional
    public PollingDevisionDto deletePollingDevision(long id) {
        PollingDevisionEntity existing = pollingDevisionRepository.findById(id)
                .orElseThrow(() -> new AppException("PollingDevision not found", HttpStatus.NOT_FOUND));
        PollingDevisionDto dto = pollingDevisionMapper.toPollingDevisionDto(existing);
        pollingDevisionRepository.delete(existing);
        return dto;
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
