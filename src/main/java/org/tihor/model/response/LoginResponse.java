package org.tihor.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Login response.
 */
@Getter
@Setter
public class LoginResponse extends CustomerResponse {
    /**
     * The Token.
     */
    private String token;

    /**
     * Instantiates a new Login response.
     *
     * @param token the token
     */
    public LoginResponse(final String token) {
        this.token = token;
    }
}
