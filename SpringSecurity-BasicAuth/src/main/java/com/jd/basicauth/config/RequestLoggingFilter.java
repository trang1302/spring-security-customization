package com.jd.basicauth.config;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logRequestDetails(request);
        filterChain.doFilter(request, response);
    }

    private void logRequestDetails(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Incoming request: ").append(request.getMethod()).append(" ").append(request.getRequestURI());

        Map<String, String> headers = getRequestHeaders(request);
        sb.append("\nHeaders: ").append(headers);

        String requestBody = getRequestBody(request);
        if (!requestBody.isEmpty()) {
            sb.append("\nBody: ").append(requestBody);
        }

        logger.info(sb.toString());
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

    private String getRequestBody(HttpServletRequest request) {
        try (InputStream inputStream = request.getInputStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Error reading request body", e);
            return "";
        }
    }
}