package com.application.document.repository;

import com.application.document.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Semih, 3.07.2023
 */
@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
