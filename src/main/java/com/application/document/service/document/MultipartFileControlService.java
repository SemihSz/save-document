package com.application.document.service.document;

import com.application.document.Constant;
import com.application.document.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * Created by Semih, 3.07.2023
 */
@RequiredArgsConstructor
@Service
public class MultipartFileControlService implements Consumer<MultipartFile> {

    private final MessageSource messageSource;

    private static final DataSize MAX_FILE_SIZE = DataSize.ofMegabytes(5); // 5 MB
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");

    /**
     * This executable function control size of file and allowed extensions.
     * If the service get an error, user cannot upload their document
     *
     * @param file MultipartFile file
     */
    @Override
    public void accept(MultipartFile file) {

        try {

            if (file == null || file.isEmpty()) {
                throw new BusinessException(messageSource.getMessage(Constant.Exception.FILE_NOT_EMPTY, null, Locale.ENGLISH));
            }

            if (file.getSize() > MAX_FILE_SIZE.toBytes()) {
                throw new FileSizeLimitExceededException(messageSource.getMessage(Constant.Exception.FILE_SIZE_LIMIT, null, Locale.ENGLISH),
                        file.getSize(), MAX_FILE_SIZE.toBytes());
            }

            final String fileExtension = getFileExtension(file.getOriginalFilename());
            if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
                throw new BusinessException(messageSource.getMessage(Constant.Exception.FILE_NOT_ALLOWED, null, Locale.ENGLISH));
            }
        } catch (FileSizeLimitExceededException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get file extension function
     *
     * @param filename
     * @return
     */
    private String getFileExtension(String filename) {

        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        }
        return "";
    }
}