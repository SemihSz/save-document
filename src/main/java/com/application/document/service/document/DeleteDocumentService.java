package com.application.document.service.document;

import com.application.document.entity.DocumentEntity;
import com.application.document.repository.DocumentRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 4.07.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteDocumentService implements SimpleTask<Long, Boolean> {

    private final DocumentRepository documentRepository;

    @Override
    public Boolean apply(Long documentId) {
        final DocumentEntity documentInfo = documentRepository.findByDocumentId(documentId);
        documentRepository.delete(documentInfo);
        return Boolean.TRUE;
    }
}
