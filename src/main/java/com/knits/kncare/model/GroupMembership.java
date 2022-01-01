package com.knits.kncare.model;

import com.knits.kncare.model.base.AbstractMemberAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member_group")
public class GroupMembership extends AbstractMemberAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "ID")
    private Member member;


    //Custom Constructor
    public GroupMembership(Group group, Member member) {
        this.group = group;
        this.member = member;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMembership)) return false;
        GroupMembership groupMembership = (GroupMembership) o;
        return Objects.equals(getGroup().getId(), groupMembership.getGroup().getId())
                && Objects.equals(getMember().getId(), groupMembership.getMember().getId());
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        GroupMembership groupMembership = (GroupMembership) o;
//        return Objects.equals(getGroup().getId(), groupMembership.getGroup().getId())
//                && Objects.equals(getMember().getId(), groupMembership.getMember().getId());
//    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "GroupMembership{" +
                "id=" + id +
                ", group=" + group +
                ", member=" + member +
                '}';
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof GroupMembership)) return false;
//        GroupMembership groupMembership = (GroupMembership) o;
//        return Objects.equals(getGroup().getId(), groupMembership.getGroup().getId())
//                && Objects.equals(getMember().getId(), groupMembership.getMember().getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
