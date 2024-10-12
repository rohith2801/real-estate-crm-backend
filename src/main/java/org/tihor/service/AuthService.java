package org.tihor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.model.request.LoginRequest;
import org.tihor.model.response.LoginResponse;
import org.tihor.repository.UserRepository;

/**
 * The type Auth service.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    /**
     * The Authentication manager.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * The Jwt service.
     */
    private final JwtService jwtService;

    /**
     * The User repository.
     */
    private final UserRepository userRepository;

    /**
     * Login login response.
     *
     * @param request the request
     * @return the login response
     */
    public LoginResponse login(final LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = userRepository.findByUsernameAndIsDeleted(request.getUsername(), false)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);

        return LoginResponse.builder().jwt(jwt).build();
    }
}
