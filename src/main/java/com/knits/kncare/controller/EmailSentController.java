package com.knits.kncare.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.knits.kncare.dto.EmailDto;
import com.knits.kncare.dto.EmailSentDto;
import com.knits.kncare.dto.Views;
import com.knits.kncare.dto.search.EmailSentSearchDto;
import com.knits.kncare.exception.EmailException;
import com.knits.kncare.service.EmailSentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequestMapping("/api/v1/emailSent")
@RestController
public class EmailSentController {

    private final EmailSentService service;

    public EmailSentController(EmailSentService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @JsonView(Views.EmailSentDetails.class)
    public ResponseEntity<EmailSentDto> getEmailSentById(@PathVariable("id") Long id) {
        Optional<EmailSentDto> emailSentDto = service.getById(id);
        return emailSentDto.map(email -> new ResponseEntity<>(email, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @JsonView(Views.EmailSentDetails.class)
    public Page<EmailSentDto> searchEmailSent(EmailSentSearchDto dto) {
        return service.search(dto);
    }

    @PostMapping("/drafts/{id}")
    @JsonView(Views.EmailSentDetails.class)
    public ResponseEntity<Set<EmailSentDto>> sendEmailDraft(@PathVariable("id") Long draftId) throws EmailException {
        return new ResponseEntity<>(service.sendDraft(draftId), HttpStatus.OK);
    }

    @PostMapping
    @JsonView(Views.EmailSentDetails.class)
    public ResponseEntity<Set<EmailSentDto>> sendEmail(@RequestBody EmailDto emailDto) throws EmailException {
        return new ResponseEntity<>(service.sendEmail(emailDto), HttpStatus.OK);
    }

}
