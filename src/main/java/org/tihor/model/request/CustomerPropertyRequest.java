package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * The type Customer property request.
 */
@Getter
@Setter
public class CustomerPropertyRequest implements Serializable {
    /**
     * The Customer info.
     */
    private CustomerRequest customerInfo;

    /**
     * The Property info.
     */
    private List<PropertyRequest> propertyInfo;
}
