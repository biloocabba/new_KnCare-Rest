package com.knits.kncare.service;

import com.knits.kncare.dto.EmployeeDto;
import com.knits.kncare.dto.MemberDto;
import com.knits.kncare.dto.pages.JsonPageImpl;
import com.knits.kncare.dto.search.EmployeeSearchDto;
import com.knits.kncare.dto.search.MemberSearchDto;
import com.knits.kncare.mapper.MemberMapper;
import com.knits.kncare.model.Member;
import com.knits.kncare.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmployeeService employeeService;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository,
                         EmployeeService employeeService,
                         MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.employeeService = employeeService;
        this.memberMapper = memberMapper;
    }

    public MemberDto add(MemberDto memberDto) {

        Long pdmId = memberDto.getEmployeePdmId();
        //1. check against EMS that this employee still exists (by pdm id)
        EmployeeSearchDto employeeSearchDto = new EmployeeSearchDto();
        employeeSearchDto.setPdmId(pdmId);
        Page<EmployeeDto> employeeFromEMS = employeeService.search(employeeSearchDto);
        if (employeeFromEMS.isEmpty()) {
            throw new RuntimeException(String.format("No Such Employee with pdm ID : %s", pdmId.toString()));
        }
        //2. validate that this employee has been not already added as member
        Optional<Member> onboardedMember = memberRepository.findByEmployeePdmId(pdmId);// throw exception here
        if (onboardedMember.isPresent()) {
            throw new RuntimeException(String.format("Employee with pdm ID : %s has already added", pdmId.toString()));
        }
        //3. assign onBoardDate to now()
        memberDto.setOnBoardDate(LocalDate.now());
        //4. onboard employee as care member
        Member createdMember = memberRepository.save(memberMapper.toModel(memberDto));
        return memberMapper.toDto(createdMember);
    }

    public MemberDto update(long id, MemberDto memberDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("No such member!"));
        member.setOffBoardDate(memberDto.getOffBoardDate());
        member.setRole(memberDto.getRole());
        memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    public MemberDto getById(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

        return memberMapper.toDto(member);
    }

    public void delete(Long id) { memberRepository.deleteById(id);}

    public void deleteAll() { memberRepository.deleteAll();}

    public Page<MemberDto> search(MemberSearchDto memberSearchDto) {
        Page<Member> memberPage = memberRepository.findAll(memberSearchDto.getSpecification(), memberSearchDto.getPageable());
        return new JsonPageImpl<>(memberMapper.toDtoList(memberPage.getContent()), memberSearchDto.getPageable(), memberSearchDto.getPageable().getPageSize());
    }
}
