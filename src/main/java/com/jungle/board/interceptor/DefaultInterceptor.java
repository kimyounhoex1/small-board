package com.jungle.board.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jungle.board.config.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DefaultInterceptor implements HandlerInterceptor{

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String getToken = request.getHeader("Authorization");
        if(getToken == null || !getToken.contains("Bearer")) {
            response.setStatus(401);
            return false;
        }
        String getTokenVal = getToken.split("Bearer ")[1];

        if(!jwtUtil.validateToken(getTokenVal)) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
    
}
