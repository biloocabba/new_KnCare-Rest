package com.knits.kncare.repository;

import com.knits.kncare.dto.EmailDto;
import com.knits.kncare.dto.search.EmailSearchDto;
import com.knits.kncare.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSearchTest {

    @Autowired
    private EmailService emailService;


    @Test
    public void findMembersByAreaOfResponsibility(){
        EmailSearchDto searchEmail = new EmailSearchDto();
        searchEmail.setRecipientId(1L);
        Page<EmailDto> emails = emailService.search(searchEmail);


    }
}
