package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Change password request.
 */
@Getter
@Setter
public class ChangePasswordRequest implements Serializable {
    /**
     * The Id.
     */
    private Long id;

    /**
     * The Current password.
     */
    private String currentPassword;

    /**
     * The New password.
     */
    private String newPassword;

    /**
     * The Confirm new password.
     */
    private String confirmNewPassword;
}
