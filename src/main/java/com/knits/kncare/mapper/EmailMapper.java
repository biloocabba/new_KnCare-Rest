package com.knits.kncare.mapper;

import com.knits.kncare.dto.EmailDto;
import com.knits.kncare.model.Email;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, GroupMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmailMapper extends CycleAvoidingMapperInterface<Email, EmailDto> {

    default Optional<EmailDto> toOptionalDto(Optional<Email> emailSent) {
        if (emailSent.isEmpty()) {
            return Optional.empty();
        }
        return emailSent.map(this::toDto);
    }

    default Page<EmailDto> toDtoPage(Page<Email> page) {
        if (page == null) {
            return null;
        }
        return page.map(this::toDto);
    }

}
