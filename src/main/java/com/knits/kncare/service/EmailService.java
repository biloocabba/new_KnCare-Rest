package com.knits.kncare.service;

import com.knits.kncare.dto.*;
import com.knits.kncare.dto.search.EmailSearchDto;
import com.knits.kncare.exception.EmailException;
import com.knits.kncare.mapper.EmailMapper;
import com.knits.kncare.mapper.GroupMapper;
import com.knits.kncare.mapper.MemberMapper;
import com.knits.kncare.model.Email;
import com.knits.kncare.model.Group;
import com.knits.kncare.model.Member;
import com.knits.kncare.repository.EmailRepository;
import com.knits.kncare.repository.GroupRepository;
import com.knits.kncare.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final MemberMapper memberMapper;
    private final GroupMapper groupMapper;
    private final EmailMapper emailMapper;

    public EmailService(EmailRepository emailRepository,
                        MemberRepository memberRepository, GroupRepository groupRepository,
                        MemberMapper memberMapper, GroupMapper groupMapper, EmailMapper emailMapper) {
        this.emailRepository = emailRepository;
        this.memberRepository = memberRepository;
        this.groupRepository = groupRepository;
        this.memberMapper = memberMapper;
        this.groupMapper = groupMapper;
        this.emailMapper = emailMapper;
    }

    public Optional<EmailDto> getById(long id) {
        return emailMapper.toOptionalDto(emailRepository.findById(id));
    }

    public EmailDto addNew(EmailDto emailDto) {
        Email email = new Email();
        email.setContent(emailDto.getContent());
        email.setSubject(emailDto.getSubject());
        email.setCreatedBy(memberRepository.findById(emailDto.getCreatedBy().getId()).orElseThrow(() -> new EmailException("No such member")));
        email = emailRepository.save(email);
        EmailRecipientChangeDto recipients = addRecipients(email.getId(), emailDto.getRecipients().stream().map(MemberDto::getId).collect(Collectors.toSet()));
        EmailGroupChangeDto recipientGroups = addGroups(email.getId(), emailDto.getRecipientGroups().stream().map(GroupDto::getId).collect(Collectors.toSet()));
        emailDto = emailMapper.toDto(email);
        emailDto.setRecipientGroups(recipientGroups.getRecipientGroups());
        emailDto.setRecipients(recipients.getRecipients());
        // TODO: 05/08/2021 Add more checks before adding new email.
        return emailDto;
    }

    public Page<EmailDto> search(EmailSearchDto emailDto) {
        Page<Email> emails = emailRepository.findAll(emailDto.getSpecification(), emailDto.getPageable());
        return emailMapper.toDtoPage(emails);
    }

    public void delete(long id) throws EmailException {
        Email email = findEmailOrThrow(id);
        emailRepository.delete(email);
    }

    public EmailDto update(long id, EmailDto emailDto) throws EmailException {
        Email email = findEmailOrThrow(id);
        email.setContent(emailDto.getContent());
        email.setSubject(emailDto.getSubject());
        emailRepository.save(email);
        return emailMapper.toDto(email);
    }

    public EmailRecipientChangeDto addRecipients(long id, Set<Long> memberIds) throws EmailException {
        Email email = findEmailOrThrow(id);
        List<Member> members = memberRepository.findAllById(memberIds);
        Set<Member> addedRecipients = members.stream().map(email::addRecipient).filter(Objects::nonNull).collect(Collectors.toSet());
        emailRepository.save(email);
        return new EmailRecipientChangeDto(email.getId(), memberMapper.toDtoSet(addedRecipients));
    }

    public EmailRecipientChangeDto deleteRecipients(long id, Set<Long> memberIds) throws EmailException {
        Email email = findEmailOrThrow(id);
        List<Member> members = memberRepository.findAllById(memberIds);

        Set<Member> deletedRecipients = members.stream().map(email::removeRecipient).filter(Objects::nonNull).collect(Collectors.toSet());
        emailRepository.save(email);
        return new EmailRecipientChangeDto(email.getId(), memberMapper.toDtoSet(deletedRecipients));
    }

    public EmailGroupChangeDto addGroups(long id, Set<Long> groupIds) throws EmailException {
        Email email = findEmailOrThrow(id);
        List<Group> groups = groupRepository.findAllById(groupIds);

        Set<Group> addedGroups = groups.stream().map(email::addGroup).filter(Objects::nonNull).collect(Collectors.toSet());
        emailRepository.save(email);
        return new EmailGroupChangeDto(email.getId(), groupMapper.toDtoSet(Set.copyOf(addedGroups)));
    }

    public EmailGroupChangeDto deleteGroups(long id, Set<Long> groupIds) throws EmailException {
        Email email = findEmailOrThrow(id);
        List<Group> groups = groupRepository.findAllById(groupIds);

        Set<Group> removedGroups = groups.stream().map(email::removeGroup).filter(Objects::nonNull).collect(Collectors.toSet());
        emailRepository.save(email);
        return new EmailGroupChangeDto(email.getId(), groupMapper.toDtoSet(removedGroups));
    }

    private Email findEmailOrThrow(long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new EmailException("No such email!"));
    }
}
