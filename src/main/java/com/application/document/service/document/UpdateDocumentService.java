package com.application.document.service.document;

import com.application.document.entity.DocumentEntity;
import com.application.document.model.Base64Files;
import com.application.document.model.DocumentInfoDTO;
import com.application.document.model.request.document.UpdateDocumentRequest;
import com.application.document.model.response.DocumentInfoResponse;
import com.application.document.repository.DocumentRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Semih, 5.07.2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateDocumentService implements SimpleTask<UpdateDocumentRequest, DocumentInfoResponse> {

    private final DocumentRepository documentRepository;

    @Override
    public DocumentInfoResponse apply(UpdateDocumentRequest updateDocumentRequest) {

        final DocumentEntity documentInfo = documentRepository.findByDocumentId(updateDocumentRequest.getDocumentId());
        final Base64Files file = updateDocumentRequest.getFile();
        if (Objects.nonNull(updateDocumentRequest.getUsername())) {
            documentInfo.setUsername(updateDocumentRequest.getUsername());
        }
        if (Objects.nonNull(file.getFileName())) {
            documentInfo.setFileName(file.getFileName());
        }
        if (Objects.nonNull(file.getFileType())) {
            documentInfo.setFileType(file.getFileType());
        }
        if (Objects.nonNull(file.getBase64Data())) {
            documentInfo.setData(file.decodeBase64Data());
        }
        documentInfo.setTime(LocalDateTime.now());
        log.info("Document entity model updated");
        documentRepository.save(documentInfo);
        log.info("Save operation is successfully");

        final DocumentInfoDTO documentInfoDTO = DocumentInfoDTO.builder()
                .documentId(documentInfo.getId())
                .username(documentInfo.getUsername())
                .userId(documentInfo.getUserId())
                .data(documentInfo.getData())
                .time(documentInfo.getTime())
                .fileType(documentInfo.getFileType())
                .fileName(documentInfo.getFileName())
                .build();
        return DocumentInfoResponse.builder()
                .document(documentInfoDTO)
                .build();
    }
}
