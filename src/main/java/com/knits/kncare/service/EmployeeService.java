package com.knits.kncare.service;

import com.knits.kncare.dto.EmployeeDto;
import com.knits.kncare.dto.pages.EmployeeDtoPage;
import com.knits.kncare.dto.pages.JsonPageImpl;
import com.knits.kncare.dto.search.EmployeeSearchDto;
import com.knits.kncare.mapper.MapperInterface;
import com.knits.kncare.model.ems.Employee;
import com.knits.kncare.repository.EmployeeRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService extends ServiceBase<Employee, EmployeeDto> {

    private final String emsSearchUrl = "http://localhost:9000/employees/search";

    private final RestTemplate restTemplate;


    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository, MapperInterface<Employee, EmployeeDto> mapper, RestTemplate restTemplate) {
        super(mapper);
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Page<EmployeeDto> getAll() {
        return new PageImpl<>(toDtoList(repository.findAll()));
    }

    public Optional<Employee> getById(long id) {
        return repository.findById(id);
    }

    public Employee add(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public Page<EmployeeDto> search(EmployeeSearchDto employeeSearch) {
            EmployeeDtoPage employeePage = restTemplate.postForObject(emsSearchUrl, employeeSearch, EmployeeDtoPage.class);

        if (employeePage!=null && employeePage.getContent().size()>0){
            updateLocalEmployeesStorage(employeePage);
            return new PageImpl<>(employeePage.getContent(), employeeSearch.getPageable(), employeePage.getContent().size());
        }
        return new JsonPageImpl<>(new ArrayList<>());
    }


        private void updateLocalEmployeesStorage(EmployeeDtoPage employeePage){

            for (EmployeeDto employeeDto:employeePage.getContent()) {
                Optional<Employee> employeeAsOpt = repository.findByPdmId(employeeDto.getPdmId());
                if (employeeAsOpt.isEmpty()){
                    repository.save(toModel(employeeDto));
                }
            }
        }


}
