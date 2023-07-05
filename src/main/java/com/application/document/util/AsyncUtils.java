package com.application.document.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

/**
 * Created by Semih, 5.07.2023
 */
@Component
public class AsyncUtils {

    public static <T> CompletableFuture<T> convertToFuture(T instance) {

        return CompletableFuture.supplyAsync(() -> instance);
    }

    public static <T> void waitAllTypedFutures(List<CompletableFuture<T>> futures) throws CompletionException, CancellationException {

        futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}

