package org.tihor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        var customerEntity = customerService.createCustomer(request.getCustomerInfo());
        propertyService.addPropertiesToCustomer(customerEntity, request.getPropertyInfo());
        return "Customer created successfully";
    }

    /**
     * Update data string.
     *
     * @param id      the id
     * @param request the request
     * @return the string
     */
    public String updateData(final Long id, final GenericRequest request) {
        var customerEntity = customerService.updateCustomer(id, request.getCustomerInfo());
        propertyService.updatePropertiesToCustomer(customerEntity, request.getPropertyInfo());
        return "Customer information updated successfully";
    }
}
