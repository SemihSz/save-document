package com.application.document.controller;

import com.application.document.model.RestResponse;
import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.model.request.document.SaveDocumentRequest;
import com.application.document.model.response.DocumentInfoResponse;
import com.application.document.service.document.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doc/v1")
@Api(value = "Document Operation", description = "Rest API for all Document operations", tags = {"document"})
public class DocumentController {

    private final DocumentService documentService;

    @ApiOperation(value = "Save document with multipart file")
    @PostMapping("/save")
    public ResponseEntity<RestResponse> uploadDocument(@ApiParam(required = true, value = "MultipartFile") @RequestParam("file") MultipartFile file,
                                                       @ApiParam(required = true, value = "Username", example = "testusername") @RequestParam("username") String username,
                                                       @ApiParam(required = true, value = "User Id", example = "1") @RequestParam("id") Long id) {
        final SaveDocumentRequest request = SaveDocumentRequest.builder()
                .file(file)
                .username(username)
                .userId(id)
                .build();

        return ResponseEntity.ok(new RestResponse<>(200, documentService.save(request)));
    }

    @PostMapping(value = "/save-base64", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> uploadBase64Document(@ApiParam(required = true, value = "Save document with base64 string request model body") @RequestBody SaveDocumentBase64Request request) {

        return ResponseEntity.ok(new RestResponse(200, documentService.saveBase64(request)));
    }

    @GetMapping("/document-list/{username}/{userId}")
    public ResponseEntity<RestResponse> documentList(@ApiParam(required = true, value = "Username", example = "testusername") @PathVariable String username,
                                                     @ApiParam(required = true, value = "User Id", example = "1") @PathVariable Long userId) {

        return ResponseEntity.ok(new RestResponse(200, documentService.documents(username, userId)));
    }

    @GetMapping("/specific-document/{documentId}")
    public ResponseEntity<RestResponse> documentInfo(@ApiParam(required = true, value = "Document Id", example = "1") @PathVariable Long documentId) {

        return ResponseEntity.ok(new RestResponse(200, documentService.documentInfo(documentId)));
    }

    @GetMapping("/download-document/{documentId}")
    public ResponseEntity<ByteArrayResource> downloadDocument(@ApiParam(required = true, value = "Document Id", example = "1") @PathVariable Long documentId) {

        final DocumentInfoResponse response = documentService.documentInfo(documentId);
        // Create a ByteArrayResource from the file data

        ByteArrayResource resource = new ByteArrayResource(response.getDocument().getData());

        return ResponseEntity.ok()
                .contentLength(response.getDocument().getData().length)
                .contentType(MediaType.parseMediaType(response.getDocument().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getDocument().getFileName() + "\"")
                .body(resource);

    }

}
