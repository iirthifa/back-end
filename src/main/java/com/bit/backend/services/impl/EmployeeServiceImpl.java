package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeMapper;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
public class EmployeeServiceImpl implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        EmployeeEntity entity = employeeMapper.toEmployeeEntity(employeeDto);
        entity.setId(null);
        clearEmploymentFields(entity);
        return employeeMapper.toEmployeeDto(employeeRepository.save(entity));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toEmployeeDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        EmployeeEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));
        return employeeMapper.toEmployeeDto(entity);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        EmployeeEntity existing = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));
        applyPersonalFields(existing, employeeDto);
        applyEmploymentFields(existing, employeeDto);
        return employeeMapper.toEmployeeDto(employeeRepository.save(existing));
    }

    @Override
    @Transactional
    public EmployeeDto deleteEmployee(long id) {
        EmployeeEntity existing = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException("Employee not found", HttpStatus.NOT_FOUND));
        EmployeeDto dto = employeeMapper.toEmployeeDto(existing);
        employeeRepository.delete(existing);
        return dto;
    }

    private void clearEmploymentFields(EmployeeEntity entity) {
        entity.setDoj(null);
        entity.setDoc(null);
        entity.setConfirmationStatus(null);
        entity.setDeptSerialId(null);
        entity.setSectSerialId(null);
        entity.setDesigSerialId(null);
        entity.setJtSerialId(null);
        entity.setGrade(null);
        entity.setScheme(null);
        entity.setBuildingSerialId(null);
        entity.setFloorSerialId(null);
        entity.setLineSerialId(null);
        entity.setShiftSerialId(null);
        entity.setBasicSalary(null);
        entity.setPreOtAllowed(null);
        entity.setPostOtAllowed(null);
        entity.setAllowedOtHrs(null);
        entity.setLateDeduct(null);
        entity.setEpfEntitled(null);
        entity.setEpfNumber(null);
        entity.setEtfNumber(null);
        entity.setBankAccNo(null);
        entity.setBankCode(null);
        entity.setBankBranchSerialId(null);
        entity.setAddAttendance(null);
        entity.setDot(null);
        entity.setTerminationType(null);
        entity.setTerminationReason(null);
        entity.setLastWorkDate(null);
        entity.setServiceLtrIssued(null);
        entity.setNotes(null);
        entity.setInactiveStatus(null);
        entity.setActive(null);
        entity.setCreatedBy(null);
        entity.setCreatedDate(null);
        entity.setModifiedBy(null);
        entity.setModifiedDate(null);
        entity.setIsDeleted(null);
        entity.setDeletedBy(null);
        entity.setDeletedDate(null);
        entity.setAttendanceBonus(null);
        entity.setAttendanceBonusAllowed(null);
        entity.setCompanyPhone2(null);
    }

    private void applyPersonalFields(EmployeeEntity existing, EmployeeDto dto) {
        setIfPresent(dto.getEeid(), existing::setEeid);
        setIfPresent(dto.getComSerialId(), existing::setComSerialId);
        setIfPresent(dto.getImageName(), existing::setImageName);
        setIfPresent(dto.getEmpRef(), existing::setEmpRef);
        setIfPresent(dto.getEmpProxId(), existing::setEmpProxId);
        setIfPresent(dto.getTitle(), existing::setTitle);
        setIfPresent(dto.getFirstName(), existing::setFirstName);
        setIfPresent(dto.getSurname(), existing::setSurname);
        setIfPresent(dto.getNameWithInitials(), existing::setNameWithInitials);
        setIfPresent(dto.getCallName(), existing::setCallName);
        setIfPresent(dto.getFullName(), existing::setFullName);
        setIfPresent(dto.getNic(), existing::setNic);
        setIfPresent(dto.getTin(), existing::setTin);
        setIfPresent(dto.getPhone1(), existing::setPhone1);
        setIfPresent(dto.getPhone2(), existing::setPhone2);
        setIfPresent(dto.getPersonalEmail(), existing::setPersonalEmail);
        setIfPresent(dto.getCompanyPhone(), existing::setCompanyPhone);
        setIfPresent(dto.getCompanyEmail(), existing::setCompanyEmail);
        setIfPresent(dto.getDob(), existing::setDob);
        setIfPresent(dto.getGender(), existing::setGender);
        setIfPresent(dto.getBloodGroup(), existing::setBloodGroup);
        setIfPresent(dto.getNationalitySerialId(), existing::setNationalitySerialId);
        setIfPresent(dto.getEthnicitySerialId(), existing::setEthnicitySerialId);
        setIfPresent(dto.getReligionSerialId(), existing::setReligionSerialId);
        setIfPresent(dto.getMaritalStatus(), existing::setMaritalStatus);
        setIfPresent(dto.getChildrenCount(), existing::setChildrenCount);
        setIfPresent(dto.getEmergencyContact(), existing::setEmergencyContact);
        setIfPresent(dto.getEmergencyContactNumber(), existing::setEmergencyContactNumber);
        setIfPresent(dto.getPermaAddress1(), existing::setPermaAddress1);
        setIfPresent(dto.getPermaAddress2(), existing::setPermaAddress2);
        setIfPresent(dto.getPermaAddress3(), existing::setPermaAddress3);
        setIfPresent(dto.getDistrictSerialId(), existing::setDistrictSerialId);
        setIfPresent(dto.getDivSectSerialId(), existing::setDivSectSerialId);
        setIfPresent(dto.getGndSerialId(), existing::setGndSerialId);
        setIfPresent(dto.getElectorateResidence(), existing::setElectorateResidence);
        setIfPresent(dto.getPollingSerialId(), existing::setPollingSerialId);
        setIfPresent(dto.getDistanceToPollingCenter(), existing::setDistanceToPollingCenter);
        setIfPresent(dto.getPermaMoh(), existing::setPermaMoh);
        setIfPresent(dto.getPoliceStationSerialId(), existing::setPoliceStationSerialId);
        setIfPresent(dto.getCurrAddress1(), existing::setCurrAddress1);
        setIfPresent(dto.getCurrAddress2(), existing::setCurrAddress2);
        setIfPresent(dto.getCurrAddress3(), existing::setCurrAddress3);
        setIfPresent(dto.getCurrDistrictSerialId(), existing::setCurrDistrictSerialId);
        setIfPresent(dto.getCurrDivSectSerialId(), existing::setCurrDivSectSerialId);
        setIfPresent(dto.getCurrGndSerialId(), existing::setCurrGndSerialId);
        setIfPresent(dto.getCurrMoh(), existing::setCurrMoh);
        setIfPresent(dto.getCurrPoliceStationSerialId(), existing::setCurrPoliceStationSerialId);
        setIfPresent(dto.getTransportRouteSerialId(), existing::setTransportRouteSerialId);
        setIfPresent(dto.getFartherName(), existing::setFartherName);
        setIfPresent(dto.getFatherContactNo(), existing::setFatherContactNo);
        setIfPresent(dto.getFatherNic(), existing::setFatherNic);
        setIfPresent(dto.getFatherStatus(), existing::setFatherStatus);
        setIfPresent(dto.getMotherName(), existing::setMotherName);
        setIfPresent(dto.getMotherContactNo(), existing::setMotherContactNo);
        setIfPresent(dto.getMotherNic(), existing::setMotherNic);
        setIfPresent(dto.getMotherStatus(), existing::setMotherStatus);
        setIfPresent(dto.getNominee1Name(), existing::setNominee1Name);
        setIfPresent(dto.getNominee1Address1(), existing::setNominee1Address1);
        setIfPresent(dto.getNominee1Address2(), existing::setNominee1Address2);
        setIfPresent(dto.getNominee1Address3(), existing::setNominee1Address3);
        setIfPresent(dto.getNominee1Nic(), existing::setNominee1Nic);
        setIfPresent(dto.getNominee1Contact1(), existing::setNominee1Contact1);
        setIfPresent(dto.getNominee1Contact2(), existing::setNominee1Contact2);
        setIfPresent(dto.getNominee1Remarks(), existing::setNominee1Remarks);
        setIfPresent(dto.getNominee2Name(), existing::setNominee2Name);
        setIfPresent(dto.getNominee2Address1(), existing::setNominee2Address1);
        setIfPresent(dto.getNominee2Address2(), existing::setNominee2Address2);
        setIfPresent(dto.getNominee2Address3(), existing::setNominee2Address3);
        setIfPresent(dto.getNominee2Nic(), existing::setNominee2Nic);
        setIfPresent(dto.getNominee2Contact1(), existing::setNominee2Contact1);
        setIfPresent(dto.getNominee2Contact2(), existing::setNominee2Contact2);
        setIfPresent(dto.getNominee2Remarks(), existing::setNominee2Remarks);
    }

    private void applyEmploymentFields(EmployeeEntity existing, EmployeeDto dto) {
        setIfPresent(dto.getDoj(), existing::setDoj);
        setIfPresent(dto.getDoc(), existing::setDoc);
        setIfPresent(dto.getConfirmationStatus(), existing::setConfirmationStatus);
        setIfPresent(dto.getDeptSerialId(), existing::setDeptSerialId);
        setIfPresent(dto.getSectSerialId(), existing::setSectSerialId);
        setIfPresent(dto.getDesigSerialId(), existing::setDesigSerialId);
        setIfPresent(dto.getJtSerialId(), existing::setJtSerialId);
        setIfPresent(dto.getGrade(), existing::setGrade);
        setIfPresent(dto.getScheme(), existing::setScheme);
        setIfPresent(dto.getBuildingSerialId(), existing::setBuildingSerialId);
        setIfPresent(dto.getFloorSerialId(), existing::setFloorSerialId);
        setIfPresent(dto.getLineSerialId(), existing::setLineSerialId);
        setIfPresent(dto.getShiftSerialId(), existing::setShiftSerialId);
        setIfPresent(dto.getBasicSalary(), existing::setBasicSalary);
        setIfPresent(dto.getPreOtAllowed(), existing::setPreOtAllowed);
        setIfPresent(dto.getPostOtAllowed(), existing::setPostOtAllowed);
        setIfPresent(dto.getAllowedOtHrs(), existing::setAllowedOtHrs);
        setIfPresent(dto.getLateDeduct(), existing::setLateDeduct);
        setIfPresent(dto.getEpfEntitled(), existing::setEpfEntitled);
        setIfPresent(dto.getEpfNumber(), existing::setEpfNumber);
        setIfPresent(dto.getEtfNumber(), existing::setEtfNumber);
        setIfPresent(dto.getBankAccNo(), existing::setBankAccNo);
        setIfPresent(dto.getBankCode(), existing::setBankCode);
        setIfPresent(dto.getBankBranchSerialId(), existing::setBankBranchSerialId);
        setIfPresent(dto.getAddAttendance(), existing::setAddAttendance);
        setIfPresent(dto.getDot(), existing::setDot);
        setIfPresent(dto.getTerminationType(), existing::setTerminationType);
        setIfPresent(dto.getTerminationReason(), existing::setTerminationReason);
        setIfPresent(dto.getLastWorkDate(), existing::setLastWorkDate);
        setIfPresent(dto.getServiceLtrIssued(), existing::setServiceLtrIssued);
        setIfPresent(dto.getNotes(), existing::setNotes);
        setIfPresent(dto.getInactiveStatus(), existing::setInactiveStatus);
        setIfPresent(dto.getActive(), existing::setActive);
        setIfPresent(dto.getCreatedBy(), existing::setCreatedBy);
        setIfPresent(dto.getCreatedDate(), existing::setCreatedDate);
        setIfPresent(dto.getModifiedBy(), existing::setModifiedBy);
        setIfPresent(dto.getModifiedDate(), existing::setModifiedDate);
        setIfPresent(dto.getIsDeleted(), existing::setIsDeleted);
        setIfPresent(dto.getDeletedBy(), existing::setDeletedBy);
        setIfPresent(dto.getDeletedDate(), existing::setDeletedDate);
        setIfPresent(dto.getAttendanceBonus(), existing::setAttendanceBonus);
        setIfPresent(dto.getAttendanceBonusAllowed(), existing::setAttendanceBonusAllowed);
        setIfPresent(dto.getCompanyPhone2(), existing::setCompanyPhone2);
    }

    private <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
