package com.knits.kncare.model;

import com.knits.kncare.model.ems.Employee;
import com.knits.kncare.model.history.RoleHistoryRecord;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"member\"") //some Db have member as sql function
public class Member {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //TODO: add index

    @Column(name = "onboard_date", nullable = false)
    private LocalDate onBoardDate;

    @Column(name = "offboard_date", nullable = false)
    private LocalDate offBoardDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<Practice> practices;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<Email> emails;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<EmailTemplate> emailTemplates;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<LearningSubscription> memberTrainingPaths;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<ScheduledTraining> memberTrainings;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private Set<RoleHistoryRecord> businessUnitHistoryRecords;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                '}';
    }
}
