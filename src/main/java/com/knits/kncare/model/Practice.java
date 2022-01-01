package com.knits.kncare.model;

import com.knits.kncare.model.base.AbstractMemberAuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "practice")
public class Practice extends AbstractMemberAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @ToString.Exclude
    @OneToMany(mappedBy = "practice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PracticeAttachment> practiceAttachments = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "practice_tags",
            joinColumns = @JoinColumn(name = "practice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<Tag> tags;

    @ManyToMany()
    @JoinTable(
            name = "practice_tags",
            joinColumns = @JoinColumn(name = "practice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rate_id", referencedColumnName = "id")
    )
    private List<Rate> rates = new ArrayList<>();


}
