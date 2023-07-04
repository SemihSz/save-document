package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.model.response.DocumentInfoResponse;
import com.application.document.model.response.DocumentListResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Semih, 2.07.2023
 *
 * Document api main service layer interface
 */
@Service
public interface DocumentService {

    /**
     * Save user document information via, multipart file format
     * @param request
     * @return
     */
    Boolean save(SaveDocumentRequest request);

    /**
     * Save document information via. base64 format
     * @param request
     * @return
     */
    Boolean saveBase64(SaveDocumentBase64Request request);

    /**
     * User documents information response
     * @param username
     * @param userId
     * @return
     */
    DocumentListResponse documents(String username, Long userId);

    /**
     * Specific selected document info
     * @param documentId
     * @return
     */
    DocumentInfoResponse documentInfo(Long documentId);

}
