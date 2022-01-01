package com.knits.kncare.model.history;

import com.knits.kncare.model.base.History;
import com.knits.kncare.model.ems.Employee;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEmployeeHistory extends History {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
