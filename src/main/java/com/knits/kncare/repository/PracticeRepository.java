package com.knits.kncare.repository;

import com.knits.kncare.model.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PracticeRepository extends JpaRepository<Practice, Long>, JpaSpecificationExecutor<Practice> {

    List<Practice> findByTitleContainingIgnoreCase(String snippet);
    List<Practice> findByDescriptionContainingIgnoreCase(String snippet);
}
