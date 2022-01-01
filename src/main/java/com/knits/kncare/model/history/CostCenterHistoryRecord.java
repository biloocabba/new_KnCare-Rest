package com.knits.kncare.model.history;

import com.knits.kncare.model.ems.CostCenter;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee_cost_center_history")
@Entity
public class CostCenterHistoryRecord extends AbstractEmployeeHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cost_center_id")
    private CostCenter costCenter;
}
