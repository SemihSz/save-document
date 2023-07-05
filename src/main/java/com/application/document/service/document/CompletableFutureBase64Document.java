package com.application.document.service.document;

import com.application.document.entity.DocumentEntity;
import com.application.document.model.Base64Files;
import com.application.document.model.request.document.SaveDocumentBase64Request;
import com.application.document.repository.DocumentRepository;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by Semih, 5.07.2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CompletableFutureBase64Document implements SimpleTask<SaveDocumentBase64Request, Boolean> {

    private final DocumentRepository documentRepository;
    /**
     * This execetuable is using completable futures ${@link java.util.concurrent.CompletableFuture} save operations.
     * @param request the function argument
     * @return
     */
    @Override
    public Boolean apply(SaveDocumentBase64Request request) {

        final Long userId = request.getUserId();
        final String username = request.getUsername();

        final ExecutorService executor = Executors.newFixedThreadPool(request.getFilesList().size());

        final List<CompletableFuture<Void>> futureList = request.getFilesList().stream()
                .map(t -> CompletableFuture.runAsync(() -> saveDatabase(t, username, userId), executor))
                .collect(Collectors.toList());

        final CompletableFuture<Void> allFutures = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        try {
            allFutures.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return Boolean.TRUE;
    }

    private void saveDatabase(Base64Files input, String username, Long userId) {

        final DocumentEntity document = DocumentEntity.builder()
                .userId(userId)
                .username(username)
                .fileType(input.getFileType())
                .fileName(input.getFileName())
                .data(input.decodeBase64Data())
                .time(LocalDateTime.now())
                .build();
        documentRepository.save(document);
        log.info("CompletableFutureBase64Document is successfully");
    }

}
