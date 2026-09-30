package com.busreservation.controller;

import com.busreservation.dto.ApiResponse;
import com.busreservation.dto.LoginRequest;
import com.busreservation.dto.RegisterRequest;
import com.busreservation.entity.User;
import com.busreservation.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User registered = userService.registerUser(request);
            return ResponseEntity.ok(ApiResponse.success("User registered successfully!", registered));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        try {
            User user = userService.authenticateUser(request);
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
            );

            SecurityContext sc = SecurityContextHolder.createEmptyContext();
            sc.setAuthentication(authToken);
            SecurityContextHolder.setContext(sc);

            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", sc);

            return ResponseEntity.ok(ApiResponse.success("Login successful!", Map.of(
                    "email", user.getEmail(),
                    "name", user.getName(),
                    "role", user.getRole()
            )));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
