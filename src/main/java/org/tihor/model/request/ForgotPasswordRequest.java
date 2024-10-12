package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Forgot password request.
 */
@Getter
@Setter
public class ForgotPasswordRequest implements Serializable {
    /**
     * The Username.
     */
    private String username;

    /**
     * The Otp.
     */
    private String otp;

    /**
     * The Password.
     */
    private String password;

    /**
     * The Confirm password.
     */
    private String confirmPassword;
}
