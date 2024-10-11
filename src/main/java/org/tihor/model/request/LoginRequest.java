package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Login request.
 */
@Getter
@Setter
public class LoginRequest implements Serializable {
    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;
}
