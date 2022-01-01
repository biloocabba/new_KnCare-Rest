package com.knits.kncare.dto.search;

import com.knits.kncare.model.EmailSent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailSentSearchDto extends AbstractSearchableDto<EmailSent> {

    private String subject;
    private Long recipientId;
    private LocalDateTime createdFrom;
    private LocalDateTime createdUntil;

    @Override
    public Specification<EmailSent> getSpecification() {
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
                Predicate recipientsFilter = criteriaBuilder.equal(root.get("recipient").get("id"), recipientId);
                filters.add(recipientsFilter);
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
