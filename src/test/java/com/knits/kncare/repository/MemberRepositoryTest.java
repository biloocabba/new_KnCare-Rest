package com.knits.kncare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MemberRepository memberRepository;



//    @Test
//    public void findMembersByAreaOfResponsibility(){
//        MemberSearch memberSearch = new MemberSearch();
//        memberSearch.setInternationalName("Mary");
//
//        Employee employee = new Employee();
//        employee.setInternationalName("Mary Smith");
//        employeeRepository.save(employee);
//        Member member = new Member();
//        member.setOnBoardDate(LocalDateTime.now());
//        member.setOffBoardDate(LocalDateTime.now());
//        member.setEmployee(employeeRepository.findFirstByOrderByIdAsc());
//        memberRepository.save(member);
//
//        Specification<Member> spec = memberRepository.findByAreaOfResponsibility(memberSearch);
//        assertThat(memberRepository.findAll(Specification.where(spec))).isNotEmpty();
//
//    }
}
