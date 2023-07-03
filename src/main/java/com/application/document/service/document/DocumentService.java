package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.model.response.DocumentInfoResponse;
import com.application.document.model.response.DocumentListResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 2.07.2023
 */

@Service
public interface DocumentService {

    /**
     * Save user document file
     * @param request
     * @return
     */
    Boolean save(SaveDocumentRequest request);

    /**
     *
     * @param request
     * @return
     */
    Boolean saveBase64(SaveDocumentBase64Request request);

    /**
     *
     * @param username
     * @param userId
     * @return
     */
    DocumentListResponse documents(String username, Long userId);

    DocumentInfoResponse documentInfo(Long documentId);

}
