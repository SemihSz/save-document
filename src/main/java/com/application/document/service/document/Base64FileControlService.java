
package com.application.document.service.document;

import com.application.document.Constant;
import com.application.document.exception.BusinessException;
import com.application.document.model.Base64Files;
import com.application.document.task.SimpleTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Created by Semih, 3.07.2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class Base64FileControlService implements SimpleTask<Base64Files, Integer> {

    private final MessageSource messageSource;

    private static final DataSize MAX_FILE_SIZE = DataSize.ofMegabytes(5); // 5 MB
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");

    @Override
    public Integer apply(Base64Files base64Files) {
        try {

            if (base64Files == null) {
                throw new BusinessException(messageSource.getMessage(Constant.Exception.FILE_NOT_EMPTY, null, Locale.ENGLISH));
            }

            if (base64Files.decodeBase64Data().length > MAX_FILE_SIZE.toBytes()) {
                throw new FileSizeLimitExceededException(messageSource.getMessage(Constant.Exception.FILE_SIZE_LIMIT, null, Locale.ENGLISH),
                        base64Files.decodeBase64Data().length, MAX_FILE_SIZE.toBytes());
            }
            String fileExtension = getFileExtension(base64Files.getFileName());
            if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
                throw new BusinessException(messageSource.getMessage(Constant.Exception.FILE_NOT_ALLOWED, null, Locale.ENGLISH));
            }
        } catch (FileSizeLimitExceededException | RuntimeException e) {
            throw new RuntimeException(e);
        }
        return base64Files.decodeBase64Data().length;
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

