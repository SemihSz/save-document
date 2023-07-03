package com.application.document.repository;

import com.application.document.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

    @Query(value = "SELECT * FROM DOCUMENT_ENTITY t where t.username=:username", nativeQuery = true)
    List<DocumentEntity> findByUsernameDocuments(@Param("username") String username);

    @Query(value = "SELECT * FROM DOCUMENT_ENTITY t where t.id=:id", nativeQuery = true)
    DocumentEntity findByDocumentId(@Param("id") Long id);
}
