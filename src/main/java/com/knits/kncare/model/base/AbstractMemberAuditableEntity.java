package com.knits.kncare.model.base;

import com.knits.kncare.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractMemberAuditableEntity extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Member createdBy;
}
