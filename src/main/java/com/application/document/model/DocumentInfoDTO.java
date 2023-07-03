package com.application.document.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Semih, 3.07.2023
 */
@Getter
@Setter
@Builder
public class DocumentInfoDTO {

    private Long documentId;

    private Long userId;

    private String username;

    private String fileName;

    private String fileType;

    public LocalDateTime time;

    private byte[] data;

}
