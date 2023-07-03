package com.application.document.service.document;

import com.application.document.entity.DocumentEntity;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.repository.DocumentRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataSize;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class SaveDocumentService implements SimpleTask<SaveDocumentRequest, Boolean> {

    private static final DataSize MAX_FILE_SIZE = DataSize.ofMegabytes(10); // 10 MB


    private final DocumentRepository documentRepository;
    @Override
    public Boolean apply(SaveDocumentRequest request) {

        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(request.getFile().getOriginalFilename()));

            try {
                if (request.getFile() != null && request.getFile().getSize() > MAX_FILE_SIZE.toBytes()) {
                throw new FileSizeLimitExceededException("File size exceeds the allowed limit.", 5, 5);
                }
            } catch (FileSizeLimitExceededException e) {
                throw new RuntimeException(e);
            }

        try {
            final DocumentEntity document = DocumentEntity.builder()
                    .userId(request.getUserId())
                    .username(request.getUsername())
                    .fileType(request.getFile().getContentType())
                    .fileName(fileName)
                    .data(request.getFile().getBytes())
                    .build();
            documentRepository.save(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Boolean.TRUE;
    }
}
