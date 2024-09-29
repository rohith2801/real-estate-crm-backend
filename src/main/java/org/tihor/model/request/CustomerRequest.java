package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Customer request.
 */
@Getter
@Setter
public class CustomerRequest implements Serializable {

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */
    private String lastName;

    /**
     * The Street.
     */
    private String street;

    /**
     * The Pin code.
     */
    private String pinCode;

    /**
     * The Location.
     */
    private String location;

    /**
     * The Cell phone.
     */
    private String cellPhone;

    /**
     * The Email id.
     */
    private String emailId;
}
