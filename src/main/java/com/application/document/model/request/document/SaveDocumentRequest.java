package com.application.document.model.request.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@Builder
public class SaveDocumentRequest {

    private String username;

    private Long userId;

    private MultipartFile file;
}
