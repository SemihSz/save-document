package com.application.document.model.request.document;

import com.application.document.model.Base64Files;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
public class SaveDocumentBase64Request {

    @ApiModelProperty(name = "username", value = "Username", example = "testuser")
    private String username;

    @ApiModelProperty(name = "userId", value = "User Id", example = "12")
    private Long userId;

    @ApiModelProperty(name = "filesList", value = "Base64Files", example = "FileListModel")
    private List<Base64Files> filesList;
}
