package com.knits.kncare.dto.search;

import com.knits.kncare.model.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailSearchDto extends AbstractSearchableDto<Email> {

    private String subject;
    private Long recipientId;
    private Long recipientGroupId;
    private LocalDateTime createdFrom;
    private LocalDateTime createdUntil;


    @Override
    public Specification<Email> getSpecification() {

        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Predicate noFiltersApplied = criteriaBuilder.conjunction();
            List<Predicate> filters = new ArrayList<>();
            filters.add(noFiltersApplied);

            if (Strings.isNotBlank(subject)) {
                Predicate subjectAsPredicate = criteriaBuilder.like(root.get("subject"), "%" + subject + "%");
                filters.add(subjectAsPredicate);
            }

            if (recipientId != null) {
                //  Join<Email, Member> emailRecipientsLeftJoin = root.join("recipients", JoinType.LEFT);
                //   Predicate recipientsFilter = criteriaBuilder.equal(emailRecipientsLeftJoin.get("id"),recipientId);
                //  filters.add(recipientsFilter);

                Predicate recipientsFilter = criteriaBuilder.equal(root.join("recipients").get("id"), recipientId);
                filters.add(recipientsFilter);
            }
            if (recipientGroupId != null) {
                Predicate recipientGroupFilter = criteriaBuilder.equal(root.join("recipientGroups").get("id"), recipientGroupId);
                filters.add(recipientGroupFilter);
            }
            if (createdFrom != null) {
                Predicate createdFromFilter = criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdFrom);
                filters.add(createdFromFilter);
            }
            if (createdUntil != null) {
                Predicate createdFromFilter = criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdUntil);
                filters.add(createdFromFilter);
            }

            return criteriaBuilder.and(filters.toArray(new Predicate[filters.size()]));
        };

    }
}
