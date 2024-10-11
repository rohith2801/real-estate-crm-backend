package org.tihor.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * The type Customer property response.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPropertyResponse implements Serializable {
    /**
     * The Customer info.
     */
    private CustomerResponse customerInfo;

    /**
     * The Property info.
     */
    private List<PropertyResponse> propertyInfo;
}
