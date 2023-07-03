package com.application.document.controller;

import com.application.document.model.RestResponse;
import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.service.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doc/v1")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/save")
    public ResponseEntity<RestResponse> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("username") String username,
                                                     @RequestParam("id") Long id) {
        final SaveDocumentRequest request = SaveDocumentRequest.builder()
                .file(file)
                .username(username)
                .userId(id)
                .build();

        return ResponseEntity.ok(new RestResponse<>(200, documentService.save(request)));
    }

    @PostMapping(value = "/save-base64", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> uploadBase64Document(@RequestBody SaveDocumentBase64Request request) {

        return ResponseEntity.ok(new RestResponse(200, documentService.saveBase64(request)));
    }


}
