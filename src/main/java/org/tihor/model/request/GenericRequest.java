package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * The type Generic request.
 */
@Getter
@Setter
public class GenericRequest implements Serializable {
    /**
     * The Customer info.
     */
    private CustomerRequest customerInfo;

    /**
     * The Property info.
     */
    private List<PropertyRequest> propertyInfo;
}
