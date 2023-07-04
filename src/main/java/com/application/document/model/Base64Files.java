package com.application.document.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@NoArgsConstructor
public class Base64Files {

    @ApiModelProperty(name = "fileName", value = "File name", example = "test.pdf")
    private String fileName;

    @ApiModelProperty(name = "fileType", value = "File type", example = "pdf")
    private String fileType;

    @ApiModelProperty(name = "base64Data", value = "Base 64 value", example = "Base 64 String")
    private String base64Data;

    public Base64Files(String fileName, String fileType, String base64Data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.base64Data = base64Data;
    }


    public byte[] decodeBase64Data() {
        return Base64.getDecoder().decode(base64Data);
    }
}
