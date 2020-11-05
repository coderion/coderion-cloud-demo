package pl.coderion.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C) Coderion sp. z o.o.
 */
@Component
public class DefaultUtilService implements UtilService {

    @Override
    public String getIpAddress() {
        HttpServletRequest request = resolveHttpServletRequest();
        if (request == null) {
            return null;
        }
        // Header X-FORWARDED-FOR - dotyczy proxy
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            // dotyczy adresu lokalnego
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress.contains(",") ? ipAddress.split(",")[0] : ipAddress;
    }

    @Override
    public boolean pinRequest() {
        HttpServletRequest request = resolveHttpServletRequest();
        if (request == null) {
            return false;
        }
        String pinRequest = request.getHeader("PIN_REQUEST");
        return Boolean.TRUE.equals(Boolean.valueOf(pinRequest));
    }

    private HttpServletRequest resolveHttpServletRequest() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        }
        return null;
    }
}
