package com.application.document.model.request.document;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(name = "username", value = "Username", example = "testuser")
    private String username;

    @ApiModelProperty(name = "userId", value = "User Id", example = "1")
    private Long userId;

    @ApiModelProperty(name = "file", value = "MultipartFile file", example = "multipart file bytes[]")
    private MultipartFile file;
}
