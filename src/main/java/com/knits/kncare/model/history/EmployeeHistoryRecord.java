package com.knits.kncare.model.history;

import com.knits.kncare.model.ems.Employee;
import com.knits.kncare.model.ems.RelationType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee_employee_history")
@Entity
public class EmployeeHistoryRecord extends AbstractEmployeeHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ref_employee_id")
    private Employee referenced;

    @Column(name = "relation_type")
    private RelationType relationType;

}
