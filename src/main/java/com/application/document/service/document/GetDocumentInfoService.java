package com.application.document.service.document;

import com.application.document.entity.DocumentEntity;
import com.application.document.model.DocumentInfoDTO;
import com.application.document.model.response.DocumentInfoResponse;
import com.application.document.repository.DocumentRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GetDocumentInfoService implements SimpleTask<Long, DocumentInfoResponse> {

    private final DocumentRepository documentRepository;

    /**
     * Get specific document information executable service
     * @param documentId the function argument
     * @return DocumentInfoResponse
     */
    @Override
    public DocumentInfoResponse apply(Long documentId) {

        final DocumentEntity documentInfo = documentRepository.findByDocumentId(documentId);

        if (Objects.nonNull(documentInfo)) {
            final DocumentInfoDTO documentInfoDTO = DocumentInfoDTO.builder()
                    .userId(documentInfo.getUserId())
                    .documentId(documentInfo.getId())
                    .fileType(documentInfo.getFileType())
                    .fileName(documentInfo.getFileName())
                    .data(documentInfo.getData())
                    .time(documentInfo.getTime())
                    .build();
            return DocumentInfoResponse.builder().document(documentInfoDTO).build();
        }

        return null;
    }
}
