package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final SaveDocumentService saveDocumentService;
    @Override
    public Boolean save(SaveDocumentRequest request) {
        return saveDocumentService.apply(request);
    }
}
