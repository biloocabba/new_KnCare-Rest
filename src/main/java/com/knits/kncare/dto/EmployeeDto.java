package com.knits.kncare.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.model.ems.*;
import com.knits.kncare.model.history.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonView(Views.Common.class)
public class EmployeeDto {


    private Long id;

    private Long pdmId;

    private String firstName;

    private String lastName;

    private String internationalName;

    private String title;

    private String email;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Date birthDate;

    private String companyPhone;

    private String companyMobilePhone;

    private Gender gender;

    private Date startDate;

    private Date endDate;

    private Date dateOfLeave;

    @JsonView(Views.EmployeeDetails.class)
    private Employee hrReference;

    @JsonView(Views.EmployeeDetails.class)
    private Employee dottedLineManager;

    @JsonView(Views.EmployeeDetails.class)
    private Employee solidLineManager;

    @JsonView(Views.EmployeeDetails.class)
    private Country nationality;

    @JsonView(Views.EmployeeDetails.class)
    private BusinessUnit businessUnit;

    @JsonView(Views.EmployeeDetails.class)
    private CostCenter costCenter;

    @JsonView(Views.EmployeeDetails.class)
    private ManagementGroup managementGroup;

    @JsonView(Views.EmployeeDetails.class)
    private WorkingPosition workingPosition;

    @JsonView(Views.EmployeeDetails.class)
    private Office office;

    @JsonView(Views.EmployeeDetails.class)
    private Set<BusinessUnitHistoryRecord> businessUnitHistoryRecords;

    @JsonView(Views.EmployeeDetails.class)
    private Set<CostCenterHistoryRecord> costCenterHistoryRecordRecords;

    @JsonView(Views.EmployeeDetails.class)
    private Set<EmployeeHistoryRecord> employeeHistoryRecords;

    @JsonView(Views.EmployeeDetails.class)
    private Set<ManagementGroupHistoryRecord> groupManagementHistoryRecords;

    @JsonView(Views.EmployeeDetails.class)
    private Set<WorkingPositionHistoryRecord> workingPositionHistoryRecords;



}
