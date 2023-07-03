package com.application.document.model.request.document;

import com.application.document.model.Base64Files;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
public class SaveDocumentBase64Request {

    private String username;

    private Long userId;

    private List<Base64Files> filesList;
}
