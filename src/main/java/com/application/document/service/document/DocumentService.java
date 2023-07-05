package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.model.request.document.UpdateDocumentRequest;
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
     * @param request SaveDocumentRequest
     * @return Boolean
     */
    Boolean save(SaveDocumentRequest request);

    /**
     * Save document information via. base64 format
     * @param request SaveDocumentBase64Request
     * @return Boolean
     */
    Boolean saveBase64(SaveDocumentBase64Request request);

    /**
     * Save document information via. base64 format usage CompletableFutures (Optional save operation for multiple files)
     * @param request SaveDocumentBase64Request
     * @return Boolean
     */
    Boolean saveBase64Futures(SaveDocumentBase64Request request);

    /**
     * User documents information response
     * @param username String
     * @param userId Long
     * @return DocumentListResponse
     */
    DocumentListResponse documents(String username, Long userId);

    /**
     * Specific selected document info
     * @param documentId Long
     * @return DocumentInfoResponse
     */
    DocumentInfoResponse documentInfo(Long documentId);

    /**
     * Delete specific selected document
     * @param documentId Long
     * @return Boolean
     */
    Boolean deleteDocument(Long documentId);

    /**
     * Update specific document information
     * @param request UpdateDocumentRequest
     * @return DocumentInfoResponse
     */
    DocumentInfoResponse updateDocument(UpdateDocumentRequest request);

}
