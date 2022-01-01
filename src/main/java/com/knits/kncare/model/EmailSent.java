package com.knits.kncare.model;

import com.knits.kncare.model.base.AbstractAuditableEntity;
import lombok.*;

import javax.persistence.*;

import static com.knits.kncare.model.status.Status.EmailSentStatus;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "email_sent")
public class EmailSent extends AbstractAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    //    @Lob() // Lob was giving some very strange errors when trying to get model from DB. Replaced with columnDefinition.
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Member recipient;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmailSentStatus status;

}
