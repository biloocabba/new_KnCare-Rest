package com.knits.kncare.email;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;

public class EmailConfig {

    private final Environment env;

    public EmailConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.mail.port"))));

        mailSender.setUsername(env.getProperty("spring.mail.host"));
        mailSender.setPassword(env.getProperty("spring.mail.host"));

//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", env.getProperty("spring.mail.host"));
//        props.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.host"));
//        props.put("mail.debug", env.getProperty("spring.mail.host"));

        return mailSender;
    }

}
