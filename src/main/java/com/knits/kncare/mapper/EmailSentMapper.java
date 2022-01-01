package com.knits.kncare.mapper;

import com.knits.kncare.dto.EmailSentDto;
import com.knits.kncare.model.EmailSent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {MemberMapper.class})
public interface EmailSentMapper extends MapperInterface<EmailSent, EmailSentDto> {

    MapperInterface<EmailSent, EmailSentDto> INSTANCE = Mappers.getMapper(EmailSentMapper.class);

    default Optional<EmailSentDto> toOptionalDto(Optional<EmailSent> emailSent) {
        if (emailSent.isEmpty()) {
            return Optional.empty();
        }
        return emailSent.map(this::toDto);
    }

    default Page<EmailSentDto> toDtoPage(Page<EmailSent> page) {
        if (page == null) {
            return null;
        }
        return page.map(this::toDto);
    }

}
