package com.knits.kncare.service;

import com.knits.kncare.dto.EmailDto;
import com.knits.kncare.dto.EmailSentDto;
import com.knits.kncare.dto.search.EmailSentSearchDto;
import com.knits.kncare.email.EmailSender;
import com.knits.kncare.exception.EmailException;
import com.knits.kncare.mapper.EmailMapper;
import com.knits.kncare.mapper.EmailSentMapper;
import com.knits.kncare.model.Email;
import com.knits.kncare.model.EmailSent;
import com.knits.kncare.model.Group;
import com.knits.kncare.model.Member;
import com.knits.kncare.model.status.Status;
import com.knits.kncare.repository.EmailRepository;
import com.knits.kncare.repository.EmailSentRepository;
import com.knits.kncare.repository.GroupRepository;
import com.knits.kncare.repository.MemberRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmailSentService {

    private final EmailSentRepository emailSentRepository;
    private final EmailRepository emailRepository;
    private final EmailSender emailSender;
    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final EmailSentMapper emailSentMapper;
    private final EmailMapper emailMapper;

    public EmailSentService(EmailSentRepository emailSentRepository, EmailRepository emailRepository,
                            EmailSender emailSender, MemberRepository memberRepository, GroupRepository groupRepository,
                            EmailSentMapper emailSentMapper, EmailMapper emailMapper) {
        this.emailSentRepository = emailSentRepository;
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
        this.memberRepository = memberRepository;
        this.groupRepository = groupRepository;
        this.emailSentMapper = emailSentMapper;
        this.emailMapper = emailMapper;
    }

    public Optional<EmailSentDto> getById(Long id) {
        return emailSentMapper.toOptionalDto(emailSentRepository.findById(id));
    }

    public Page<EmailSentDto> search(EmailSentSearchDto dto) {
        return emailSentMapper.toDtoPage(emailSentRepository.findAll(dto.getSpecification(), dto.getPageable()));
    }

    public Set<EmailSentDto> sendDraft(Long draftIds) throws EmailException {

        Email draft = emailRepository.findById(draftIds)
                .orElseThrow(() -> new EmailException("No such draft"));

        return emailSentMapper.toDtoSet(sendDraftEmail(draft));

    }


    public Set<EmailSentDto> sendEmail(EmailDto emailDto) throws EmailException {
        Email email = emailMapper.toModel(emailDto);
        Set<Member> recipients = email.getRecipients().stream()
                .map(Member::getId)
                .map(memberRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        Set<Group> recipientGroups = email.getRecipientGroups().stream()
                .map(Group::getId)
                .map(groupRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        email.setCreatedBy(memberRepository.findById(email.getCreatedBy().getId())
                .orElseThrow(() -> new EmailException("No such member")));
        email.setRecipients(recipients);
        email.setRecipientGroups(recipientGroups);

        return emailSentMapper.toDtoSet(sendDraftEmail(email));
    }

    private Set<EmailSent> sendDraftEmail(Email draft) throws EmailException {
        verifyDraft(draft);
        try {
            return emailSender.sendDraft(draft);
        } catch (MessagingException e) {
            throw new EmailException(e);
        }
    }

    private void verifyDraft(Email draft) {
        if (!Strings.isNotBlank(draft.getContent())) {
            throw new EmailException("Content cannot be empty");
        }
        if (!Strings.isNotBlank(draft.getSubject())) {
            throw new EmailException("Subject cannot be empty");
        }
        if (draft.getRecipientGroups().isEmpty() && draft.getRecipients().isEmpty()) {
            throw new EmailException("Email must have recipients");
        }
        if (draft.getCreatedBy() == null) {
            // TODO: 18/08/2021 Replace with authentication when working
            throw new EmailException("Email must have a creator");
        }

    }

    public static EmailSent draftToSent(Email draft, Member recipient) {
        return EmailSent.builder()
                .content(draft.getContent())
                .subject(draft.getSubject())
                .recipient(recipient)
                .status(Status.EmailSentStatus.PENDING)
                .build();

    }
}
