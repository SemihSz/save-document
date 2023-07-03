package com.application.document.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * Created by Semih, 2.07.2023
 */

@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Log the request
        log.info("Request URI: {}", request.getRequestURI());
        log.info("Request Method: {}", request.getMethod());
        logRequestBody(request);

        // Proceed with the filter chain
        filterChain.doFilter(request, response);

        // Log the response
        log.info("Response Status: {}", response.getStatus());
        // Log other response details as needed
    }

    private void logRequestBody(HttpServletRequest request) throws IOException {
        String requestBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("Request Body: {}", requestBody);
    }
}
