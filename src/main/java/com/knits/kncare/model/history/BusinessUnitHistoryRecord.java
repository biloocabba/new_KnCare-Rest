package com.knits.kncare.model.history;

import com.knits.kncare.model.ems.BusinessUnit;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee_business_unit_history")
@Entity
public class BusinessUnitHistoryRecord extends AbstractEmployeeHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_unit_id")
    private BusinessUnit businessUnit;

}
