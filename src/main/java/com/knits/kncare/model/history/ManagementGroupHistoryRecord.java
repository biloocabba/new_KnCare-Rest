package com.knits.kncare.model.history;

import com.knits.kncare.model.ems.ManagementGroup;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee_working_group_history")
@Entity
public class ManagementGroupHistoryRecord extends AbstractEmployeeHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "management_group_id")
    private ManagementGroup managementGroup;
}
