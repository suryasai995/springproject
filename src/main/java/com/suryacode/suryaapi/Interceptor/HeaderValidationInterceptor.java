package com.suryacode.suryaapi.Interceptor;
// package com.suryacode.suryaapi.middleware;

import com.suryacode.suryaapi.exception.Checkboxidsessionid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HeaderValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String boxid = request.getHeader("box-id");
        String sessionid = request.getHeader("session-id");

        if (boxid == null || boxid.isEmpty() || sessionid == null || sessionid.isEmpty()) {
            throw new Checkboxidsessionid("Missing headers: boxid or sessionid");
        }

        return true; // Continue to controller
    }
}
