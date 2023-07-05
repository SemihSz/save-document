package com.application.document.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

@Slf4j
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append("Incoming request: ")
                .append(httpServletRequest.getMethod())
                .append(" ")
                .append(httpServletRequest.getRequestURI())
                .append(System.lineSeparator());

        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = httpServletRequest.getHeader(headerName);
            requestInfo.append(headerName)
                    .append(": ")
                    .append(headerValue)
                    .append(System.lineSeparator());
        }

//        requestInfo.append("Body: ")
//                .append(getRequestPayload(httpServletRequest));

        log.info(requestInfo.toString());

        //filterChain.doFilter(servletRequest, servletResponse);

        log.info("Outgoing response: {}", httpServletResponse.getStatus());
    }

    private String getRequestPayload(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            log.error("Error reading request payload", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error("Error closing BufferedReader", e);
                }
            }
        }
        return stringBuilder.toString();
    }
}