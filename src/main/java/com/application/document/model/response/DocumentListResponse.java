package com.application.document.model.response;

import com.application.document.model.DocumentInfoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@Builder
public class DocumentListResponse {

    private List<DocumentInfoDTO> documents;
}
