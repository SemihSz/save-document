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

        public Exception() {
        }
    }

    public Constant() {
    }
}
