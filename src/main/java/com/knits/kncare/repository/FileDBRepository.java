package com.knits.kncare.repository;

import com.knits.kncare.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Long> {
    FileDB findFirstByOrderByIdAsc();
}
