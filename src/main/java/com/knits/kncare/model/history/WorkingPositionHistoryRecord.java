package com.knits.kncare.model.history;

import com.knits.kncare.model.ems.WorkingPosition;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee_working_position_history")
@Entity
public class WorkingPositionHistoryRecord extends AbstractEmployeeHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "working_position_id")
    private WorkingPosition workingPosition;
}
