package com.xm.api.security;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityService {

    /**
     * Returns client IP from which the request was made. {@code X-Forwarded-For} header is also checked in case the
     * service is located behind a load balancer.
     *
     * @return client IP.
     */
    public String getClientIP(HttpServletRequest request) {
        String xForwardedHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedHeader == null) {
            return request.getRemoteAddr();
        }
        return xForwardedHeader.split(",")[0];
    }
}