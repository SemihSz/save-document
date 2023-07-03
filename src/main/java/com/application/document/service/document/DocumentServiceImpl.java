package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Created by Semih, 3.07.2023
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final SaveDocumentService saveDocumentService;

    private final SaveBase64DocumentService saveBase64DocumentService;
    @Override
    public Boolean save(SaveDocumentRequest request) {
        return saveDocumentService.apply(request);
    }

    @Override
    public Boolean saveBase64(SaveDocumentBase64Request request) {
        return saveBase64DocumentService.apply(request);
    }
}
