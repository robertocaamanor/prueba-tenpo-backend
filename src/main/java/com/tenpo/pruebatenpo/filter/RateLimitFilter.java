package com.tenpo.pruebatenpo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter implements Filter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        if (requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/v3/api-docs") || requestURI.equals("/transaction") || (method.equals("GET") && requestURI.matches("/transaction/\\d+"))|| (method.equals("DELETE") && requestURI.matches("/transaction/\\d+"))) {
            chain.doFilter(request, response);
            return;
        }

        String clientIp = httpRequest.getRemoteAddr();
        Bucket bucket = buckets.computeIfAbsent(clientIp, this::newBucket);

        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(429); // Código de estado para "Too Many Requests"
            httpResponse.setContentType("application/json;charset=UTF-8");

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("timestamp", LocalDateTime.now());
            responseBody.put("status", 429);
            responseBody.put("error", "Too Many Requests");
            responseBody.put("message", "Ha superado el límite de transacciones permitido en esta aplicación, espere un minuto");
            responseBody.put("path", httpRequest.getRequestURI());

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String jsonResponse = mapper.writeValueAsString(responseBody);

            httpResponse.getWriter().write(jsonResponse);
        }
    }

    @Override
    public void destroy() {
    }

    private Bucket newBucket(String clientIp) {
        Refill refill = Refill.greedy(3, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(3, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }
}