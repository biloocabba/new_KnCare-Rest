package com.knits.kncare.repository;

import com.knits.kncare.model.PracticeAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeAttachmentRepository extends JpaRepository<PracticeAttachment, Long> {
}
