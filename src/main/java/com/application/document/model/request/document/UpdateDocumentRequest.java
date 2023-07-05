package com.application.document.model.request.document;

import com.application.document.model.Base64Files;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Semih, 5.07.2023
 */
@Getter
@Setter
public class UpdateDocumentRequest {

    @ApiModelProperty(name = "username", value = "Username", example = "testuser")
    private String username;

    @ApiModelProperty(name = "documentId", value = "Document Id", example = "1")
    private Long documentId;

    @ApiModelProperty(name = "file", value = "Base64Files ", example = "Base64Files body")
    private Base64Files file;
}
