package com.application.document.service.document;

import com.application.document.entity.DocumentEntity;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.repository.DocumentRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class SaveDocumentService implements SimpleTask<SaveDocumentRequest, Boolean> {

    private final MultipartFileControlService fileControlService;

    private final DocumentRepository documentRepository;

    @Override
    public Boolean apply(SaveDocumentRequest request) {

        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(request.getFile().getOriginalFilename()));

        /**
         * Control file size and allowed extensions
         */
        try {
            fileControlService.accept(request.getFile());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            final DocumentEntity document = DocumentEntity.builder()
                    .userId(request.getUserId())
                    .username(request.getUsername())
                    .fileType(request.getFile().getContentType())
                    .fileName(fileName)
                    .data(request.getFile().getBytes())
                    .time(LocalDateTime.now())
                    .build();
            documentRepository.save(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Boolean.TRUE;
    }
}
