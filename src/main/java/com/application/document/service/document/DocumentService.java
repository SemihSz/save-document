package com.application.document.service.document;

import com.application.document.model.request.document.SaveDocumentRequest;
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

}
