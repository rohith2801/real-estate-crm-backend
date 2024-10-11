package org.tihor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tihor.model.Response;
import org.tihor.model.request.LoginRequest;
import org.tihor.service.AuthService;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    /**
     * The Auth service.
     */
    private final AuthService authService;

    /**
     * Login response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody final LoginRequest request) {
        return ResponseEntity.ok(Response.withData(authService.login(request)));
    }
}
