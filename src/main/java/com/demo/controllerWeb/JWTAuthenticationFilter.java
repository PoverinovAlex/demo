package com.demo.controllerWeb;

import com.demo.services.SecurityService.MyUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private MyUserService myUserService;

    public JWTAuthenticationFilter(JWTUtil jwtUtil, MyUserService myUserService) {
        this.jwtUtil = jwtUtil;
        this.myUserService = myUserService;
    }

/*    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse
            response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token != null && jwtUtil.validateToken(token)) {
            Authentication authentication = jwtUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);
            if (token != null) {
                try {
                    if (jwtUtil.validateToken(token)) {
                        Authentication authentication = jwtUtil.getAuthentication(token);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        // Если токен невалиден, отправляем ошибку 401 Unauthorized
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Invalid token");
                        return;
                    }
                } catch (Exception ex) {
                    // Логирование ошибки
                    System.err.println("Cannot validate token: " + ex.getMessage());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Invalid token");
                    return;
                }
            } else {
                // Если токен отсутствует, продолжаем цепочку фильтров
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception ex) {
            // Логирование ошибки
            System.err.println("Cannot set user authentication: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            return;
        }
        filterChain.doFilter(request, response);
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
