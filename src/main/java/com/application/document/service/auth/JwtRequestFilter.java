package com.application.document.service.auth;

import com.application.document.Constant;
import com.application.document.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Semih, 3.07.2023
 */

@Slf4j

public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MessageSource messageSource;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHORIZATION_BEARER = "Bearer ";

    private static final int BEGIN_INDEX = 7;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        String username = null;
        String jwt = null;

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(AUTHORIZATION_BEARER)) {
            jwt = authorizationHeader.substring(BEGIN_INDEX);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                logger.error(messageSource.getMessage(Constant.Exception.AUTHORIZATION_HEADER, null, Locale.ENGLISH), e);
            } catch (ExpiredJwtException e) {
                logger.warn(messageSource.getMessage(Constant.Exception.AUTHORIZATION_EXPIRED, null, Locale.ENGLISH), e);
            } catch (MalformedJwtException e) {
                logger.error(messageSource.getMessage(Constant.Exception.AUTHORIZATION_VALIDATION, null, Locale.ENGLISH), e);
            }
        } else {
            logger.warn(messageSource.getMessage(Constant.Exception.AUTHORIZATION_HEADER, null, Locale.ENGLISH));
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // TODO This area affected response and HTTP status.
//        String requestBody = getRequestBody(request);
//        log.info(requestBody);
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(request, response);
        // TODO This area affected response and HTTP status.

//        String responseBody = getResponseBody(response);
//        log.info(responseBody);
//        responseWrapper.copyBodyToResponse();

    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        InputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }

    private String getResponseBody(HttpServletResponse response) throws IOException {
        String responseBody = "";
        if (response instanceof ContentCachingResponseWrapper) {
            ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
            byte[] responseBytes = responseWrapper.getContentAsByteArray();
            if (responseBytes.length > 0) {
                responseBody = new String(responseBytes, response.getCharacterEncoding());
            }
            responseWrapper.copyBodyToResponse();
        }
        return responseBody;
    }

}
