package com.bit.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String eeid;

    private Long comSerialId;

    @Column(length = 255)
    private String imageName;

    @Column(length = 50)
    private String empRef;

    @Column(length = 50)
    private String empProxId;

    @Column(length = 20)
    private String title;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String surname;

    @Column(length = 150)
    private String nameWithInitials;

    @Column(length = 100)
    private String callName;

    @Column(length = 200)
    private String fullName;

    @Column(length = 20)
    private String nic;

    @Column(length = 30)
    private String tin;

    @Column(length = 30)
    private String phone1;

    @Column(length = 30)
    private String phone2;

    @Column(length = 150)
    private String personalEmail;

    @Column(length = 30)
    private String companyPhone;

    @Column(length = 150)
    private String companyEmail;

    private LocalDate dob;

    @Column(length = 20)
    private String gender;

    @Column(length = 10)
    private String bloodGroup;

    private Long nationalitySerialId;

    private Long ethnicitySerialId;

    private Long religionSerialId;

    @Column(length = 30)
    private String maritalStatus;

    private Integer childrenCount;

    @Column(length = 150)
    private String emergencyContact;

    @Column(length = 30)
    private String emergencyContactNumber;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String permaAddress1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String permaAddress2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String permaAddress3;

    private Long districtSerialId;

    private Long divSectSerialId;

    private Long gndSerialId;

    @Column(length = 150)
    private String electorateResidence;

    private Long pollingSerialId;

    @Column(precision = 10, scale = 2)
    private BigDecimal distanceToPollingCenter;

    @Column(length = 150)
    private String permaMoh;

    private Long policeStationSerialId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String currAddress1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String currAddress2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String currAddress3;

    private Long currDistrictSerialId;

    private Long currDivSectSerialId;

    private Long currGndSerialId;

    @Column(length = 150)
    private String currMoh;

    private Long currPoliceStationSerialId;

    private Long transportRouteSerialId;

    @Column(length = 150)
    private String fartherName;

    @Column(length = 30)
    private String fatherContactNo;

    @Column(length = 20)
    private String fatherNic;

    @Column(length = 30)
    private String fatherStatus;

    @Column(length = 150)
    private String motherName;

    @Column(length = 30)
    private String motherContactNo;

    @Column(length = 20)
    private String motherNic;

    @Column(length = 30)
    private String motherStatus;

    @Column(length = 150)
    private String nominee1Name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee1Address1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee1Address2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee1Address3;

    @Column(length = 20)
    private String nominee1Nic;

    @Column(length = 30)
    private String nominee1Contact1;

    @Column(length = 30)
    private String nominee1Contact2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee1Remarks;

    @Column(length = 150)
    private String nominee2Name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee2Address1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee2Address2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee2Address3;

    @Column(length = 20)
    private String nominee2Nic;

    @Column(length = 30)
    private String nominee2Contact1;

    @Column(length = 30)
    private String nominee2Contact2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String nominee2Remarks;

    private LocalDate doj;

    private LocalDate doc;

    @Column(length = 30)
    private String confirmationStatus;

    private Long deptSerialId;

    private Long sectSerialId;

    private Long desigSerialId;

    private Long jtSerialId;

    @Column(length = 30)
    private String grade;

    @Column(length = 50)
    private String scheme;

    private Long buildingSerialId;

    private Long floorSerialId;

    private Long lineSerialId;

    private Long shiftSerialId;

    @Column(precision = 18, scale = 2)
    private BigDecimal basicSalary;

    private Boolean preOtAllowed;

    private Boolean postOtAllowed;

    @Column(precision = 10, scale = 2)
    private BigDecimal allowedOtHrs;

    private Boolean lateDeduct;

    private Boolean epfEntitled;

    @Column(length = 50)
    private String epfNumber;

    @Column(length = 50)
    private String etfNumber;

    @Column(length = 50)
    private String bankAccNo;

    @Column(length = 20)
    private String bankCode;

    private Long bankBranchSerialId;

    private Boolean addAttendance;

    private LocalDate dot;

    @Column(length = 50)
    private String terminationType;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String terminationReason;

    private LocalDate lastWorkDate;

    private Boolean serviceLtrIssued;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(length = 30)
    private String inactiveStatus;

    private Boolean active;

    private Long createdBy;

    private LocalDateTime createdDate;

    private Long modifiedBy;

    private LocalDateTime modifiedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private Long deletedBy;

    private LocalDateTime deletedDate;

    @Column(precision = 18, scale = 2)
    private BigDecimal attendanceBonus;

    private Boolean attendanceBonusAllowed;

    @Column(length = 30)
    private String companyPhone2;
}
