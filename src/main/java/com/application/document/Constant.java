package com.application.document;

import org.springframework.stereotype.Component;

/**
 * Created by Semih, 3.07.2023
 */
@Component
public class Constant {

    public static class Exception {

        public static final String AUTH_USER_EXIST = "authentication.user_exits";

        public static final String AUTH_EMAIL_EXIST = "authentication.email_exits";

        public static final String FILE_NOT_EMPTY = "file.not_empty";

        public static final String FILE_NOT_ALLOWED = "file.not_allowed";

        public static final String FILE_SIZE_LIMIT = "file_size_limit";

        public Exception() {
        }
    }

    public Constant() {
    }
}
