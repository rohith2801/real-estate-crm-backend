package org.tihor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tihor.entity.CustomerEntity;
import org.tihor.model.request.GenericRequest;

/**
 * The type Generic service.
 */
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GenericService {
    /**
     * The Customer service.
     */
    private final CustomerService customerService;

    /**
     * The Property service.
     */
    private final PropertyService propertyService;

    /**
     * Create data string.
     *
     * @param request the request
     * @return the string
     */
    public String createData(final GenericRequest request) {
        CustomerEntity customerEntity = customerService.createCustomer(request.getCustomerInfo());
        propertyService.addPropertiesToCustomer(
                customerEntity,
                request.getPropertyInfo()
        );

        return "Customer created successfully";
    }
}
