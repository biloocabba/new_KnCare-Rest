package com.knits.kncare.dto.search;

import com.knits.kncare.model.Member;
import com.knits.kncare.model.ems.*;
import com.knits.kncare.utils.Specifications;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MemberSearchDto extends AbstractSearchableDto<Member> {

    private String internationalName;

    private Long nationalityId;

    private Long officeId;

    private Long businessUnitId;

    private Long managementGroupId;

    private Long costCenterId;

    @Override
    public Specification<Member> getSpecification() {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true); //otherwise will list a Member for every matching row in join
            Predicate noFiltersApplied = criteriaBuilder.conjunction(); //default to no filters
            List<Predicate> filters = new ArrayList<>();
            filters.add(noFiltersApplied); //support list all for empty search dto

            if (Strings.isNotBlank(internationalName)) {
                Predicate firstNameAsPredicate = criteriaBuilder.like(root.get("employee").get("internationalName"), "%" + internationalName + "%");
                filters.add(firstNameAsPredicate);
            }
            if (nationalityId != null) {
                Predicate businessUnitIdAsPredicate = criteriaBuilder.equal(root.get("employee").get("nationality").get("id"), nationalityId);
                filters.add(businessUnitIdAsPredicate);
            }
            if (officeId != null) {
                Predicate businessUnitIdAsPredicate = criteriaBuilder.equal(root.get("employee").get("office").get("id"), officeId);
                filters.add(businessUnitIdAsPredicate);
            }
            if (businessUnitId != null) {
                Predicate businessUnitIdAsPredicate = criteriaBuilder.equal(root.get("employee").get("businessUnit").get("id"), businessUnitId);
                filters.add(businessUnitIdAsPredicate);
            }
            if (managementGroupId != null) {
                Predicate businessUnitIdAsPredicate = criteriaBuilder.equal(root.get("employee").get("managementGroup").get("id"), managementGroupId);
                filters.add(businessUnitIdAsPredicate);
            }
            if (costCenterId != null) {
                Predicate businessUnitIdAsPredicate = criteriaBuilder.equal(root.get("employee").get("costCenter").get("id"), costCenterId);
                filters.add(businessUnitIdAsPredicate);
            }
            return criteriaBuilder.and(filters.toArray(new Predicate[filters.size()]));
        };
    }
}
