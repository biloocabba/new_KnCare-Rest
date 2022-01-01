package com.knits.kncare.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.EmailDto;
import com.knits.kncare.dto.MemberDto;
import com.knits.kncare.dto.Views;
import com.knits.kncare.dto.search.MemberSearchDto;
import com.knits.kncare.model.Member;
import com.knits.kncare.repository.EmployeeRepository;
import com.knits.kncare.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/members")
@RestController
@JsonView(Views.Common.class)
@Slf4j
public class MemberController {
    private final MemberService service;
    private final EmployeeRepository repository;


    @Autowired
    public MemberController(MemberService service, EmployeeRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Operation(summary = "find a care member by id")
    @GetMapping("{id}")
    @JsonView(Views.MemberDetails.class)
    public ResponseEntity<MemberDto> getMemberById(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }


    @Operation(summary = "create a care member")
    @PostMapping
    @JsonView(Views.MemberDetails.class)
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        try {
            return new ResponseEntity<>(service.add(memberDto), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "update a Member")
    @PutMapping("{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable("id") long id, @RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(service.update(id, memberDto), HttpStatus.OK);
    }


    @Operation(summary = "delete a care member by id")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable("id") long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "delete all care members")
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllMembers() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(summary = "find care members by one of its model fields")
    @GetMapping
    @JsonView(Views.Common.class)
    public ResponseEntity<Page<MemberDto>> searchMembers(MemberSearchDto memberSearchDto) {
        try {
            return new ResponseEntity<>(service.search(memberSearchDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
