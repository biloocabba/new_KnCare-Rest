package com.knits.kncare.dto.search;

import com.knits.kncare.dto.search.AbstractSearchableDto;
import com.knits.kncare.model.ems.*;
import com.knits.kncare.utils.Specifications;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSearchDto extends AbstractSearchableDto<Employee> {

    private Long pdmId;

    private String internationalName;

    private String title;

    private Long country;

    private Long businessUnit;

    private Long managementGroup;

    private Long workingPosition;

    private Long costCenter;


//    public Specification<Employee> search(Specification<Employee> spec, EmployeeSearch employeeSearch) {
//        if (Strings.isNotBlank(employeeSearch.getFirstName())){
//            spec = spec.and(Specifications.specLike("firstName", "%" + employeeSearch.getFirstName() + "%")) ;
//        }
//        if (Strings.isNotBlank(employeeSearch.getLastName())){
//            spec = spec.and(Specifications.specLike("lastName", "%" + employeeSearch.getLastName() + "%")) ;
//        }
//        if (Strings.isNotBlank(employeeSearch.getInternationalName())){
//            spec = spec.and(Specifications.specLike("internationalName", "%" + employeeSearch.getInternationalName() + "%")) ;
//        }
//        if (Strings.isNotBlank(employeeSearch.getTitle())){
//            spec = spec.and(Specifications.specLike("title", "%" + employeeSearch.getTitle() + "%")) ;
//        }
//        if (employeeSearch.getNationality() != null){
//            spec = spec.and(Specifications.specEquals("nationality", employeeSearch.getNationality().getId()));
//        }
//        if (employeeSearch.getBusinessUnit() != null){
//            spec = spec.and(Specifications.specEquals("businessUnit", employeeSearch.getBusinessUnit().getId()));
//        }
//
//        return spec;
//    }

}
