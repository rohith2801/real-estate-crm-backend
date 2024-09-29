package org.tihor.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * The type Customer response.
 */
@Getter
@Setter
@Builder
public class CustomerResponse implements Serializable {
    /**
     * The Id.
     */
    private Long id;

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

    /**
     * The Property responses.
     */
    private List<PropertyResponse> propertyResponses;
}
