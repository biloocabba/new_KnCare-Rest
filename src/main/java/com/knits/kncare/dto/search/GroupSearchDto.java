package com.knits.kncare.dto.search;


import com.knits.kncare.model.Group;
import com.knits.kncare.utils.Specifications;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;


public class GroupSearchDto extends AbstractSearchableDto {

    private String name;


    @Override
    public Specification<Group> getSpecification() {
        Specification<Group> spec = super.getSpecification();
        if (Strings.isNotBlank(name)) {
            spec = spec.and(Specifications.specLike("name", "%"+name+"%"));
        }

        return spec;
    }

}
