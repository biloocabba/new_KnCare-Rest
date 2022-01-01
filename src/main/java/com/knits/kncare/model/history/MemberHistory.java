package com.knits.kncare.model.history;

import com.knits.kncare.model.Member;
import com.knits.kncare.model.base.History;
import com.knits.kncare.model.ems.Employee;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@MappedSuperclass
public class MemberHistory extends History {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
