package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.model.response.DocumentInfoResponse;
import com.application.document.model.response.DocumentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Created by Semih, 3.07.2023
 * Document api main service impl layer.
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final SaveDocumentService saveDocumentService;

    private final SaveBase64DocumentService saveBase64DocumentService;

    private final GetDocumentListService getDocumentListService;

    private final GetDocumentInfoService getDocumentInfoService;

    @Override
    public Boolean save(SaveDocumentRequest request) {
        return saveDocumentService.apply(request);
    }

    @Override
    public Boolean saveBase64(SaveDocumentBase64Request request) {
        return saveBase64DocumentService.apply(request);
    }

    @Override
    public DocumentListResponse documents(String username, Long userId) {
        return getDocumentListService.apply(username, userId);
    }

    @Override
    public DocumentInfoResponse documentInfo(Long documentId) {
        return getDocumentInfoService.apply(documentId);
    }
}
