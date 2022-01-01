package com.knits.kncare.email;

import com.knits.kncare.model.Email;
import com.knits.kncare.model.EmailSent;
import com.knits.kncare.model.Group;
import com.knits.kncare.model.Member;
import com.knits.kncare.model.status.Status;
import com.knits.kncare.repository.EmailSentRepository;
import com.knits.kncare.service.EmailSentService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmailSender {

    private final JavaMailSender emailSender;
    private final EmailSentRepository emailRepository;

    public EmailSender(JavaMailSender emailSender, EmailSentRepository emailRepository) {
        this.emailSender = emailSender;
        this.emailRepository = emailRepository;
    }

    public Set<EmailSent> sendDraft(Email draft) throws MessagingException {

        Set<Member> recipients = draft.getRecipients();
        recipients.addAll(
                draft.getRecipientGroups().stream()
                        .map(Group::getMembers)
                        .flatMap(Set::stream)
                        .collect(Collectors.toSet())
        );
        Set<EmailSent> emails = new HashSet<>();
        for (Member recipient : recipients) {
            EmailSent email = emailRepository.save(EmailSentService.draftToSent(draft, recipient));
            boolean isSent = sendEmail(draft.getCreatedBy(), recipient, draft.getSubject(), draft.getContent(), new HashMap<>());
            if (isSent) {
                email.setStatus(Status.EmailSentStatus.CONFIRMED);
            } else {
                email.setStatus(Status.EmailSentStatus.BOUNCED);
            }
            emails.add(emailRepository.save(email));
        }
        return emails;
    }

    public boolean sendEmail(Member sender, Member recipient, String subject, String contents, Map<String, String> attachments) throws MessagingException {
        MimeMessage email = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(email, true);

        helper.setFrom(sender.getEmployee().getEmail());
        helper.setTo(recipient.getEmployee().getEmail());
        helper.setSubject(subject);
        helper.setText(contents);


        for (Map.Entry<String, String> entry: attachments.entrySet()) {
            FileSystemResource file
                    = new FileSystemResource(new File(entry.getValue()));
            helper.addAttachment(entry.getKey(), file);
        }

        try {
            emailSender.send(email);
            return true;
        } catch (MailException e) {
            return false;
        }

    }



}
