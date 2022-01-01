package com.knits.kncare.repository;

import com.knits.kncare.model.EmailSent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSentRepository extends JpaRepository<EmailSent, Long>, JpaSpecificationExecutor<EmailSent> {
}
