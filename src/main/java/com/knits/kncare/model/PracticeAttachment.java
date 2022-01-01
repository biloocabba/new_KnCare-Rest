package com.knits.kncare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.knits.kncare.model.base.AbstractMemberAuditableEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "practice_attachment")
public class PracticeAttachment extends AbstractMemberAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="file_id", nullable = false)
    private FileDB fileDB;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="practice_id", nullable = false)
    private Practice practice;
}
