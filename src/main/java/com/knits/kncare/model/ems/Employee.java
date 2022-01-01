package com.knits.kncare.model.ems;


import com.knits.kncare.model.history.*;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pdm_id")
    private Long pdmId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "international_name")
    private String internationalName;

    @Column(name = "title")
    private String title;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_mobile_phone")
    private String companyMobilePhone;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "date_of_leave")
    private Date dateOfLeave;

    @ManyToOne
    @JoinColumn(name = "hr_reference_id", referencedColumnName = "id")
    private Employee hrReference;

    @ManyToOne
    @JoinColumn(name = "dotted_line_manager_id", referencedColumnName = "id")
    private Employee dottedLineManager;

    @ManyToOne
    @JoinColumn(name = "solid_line_manager_id", referencedColumnName = "id")
    private Employee solidLineManager;

    @ManyToOne
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    private Country nationality;

    @ManyToOne
    @JoinColumn(name = "business_unit_id")
    private BusinessUnit businessUnit;

    @ManyToOne
    @JoinColumn(name = "cost_center_id")
    private CostCenter costCenter;

    @ManyToOne
    @JoinColumn(name = "management_group_id")
    private ManagementGroup managementGroup;

    @ManyToOne
    @JoinColumn(name = "working_position_id")
    private WorkingPosition workingPosition;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;


    @OneToMany(mappedBy = "employee")
    private Set<BusinessUnitHistoryRecord> businessUnitHistoryRecords;

    @OneToMany(mappedBy = "employee")
    private Set<CostCenterHistoryRecord> costCenterHistoryRecordRecords;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeHistoryRecord> employeeHistoryRecords;

    @OneToMany(mappedBy = "employee")
    private Set<ManagementGroupHistoryRecord> groupManagementHistoryRecords;

    @OneToMany(mappedBy = "employee")
    private Set<WorkingPositionHistoryRecord> workingPositionHistoryRecords;


}
