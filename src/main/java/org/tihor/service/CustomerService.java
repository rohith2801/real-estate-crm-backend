package org.tihor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tihor.entity.CustomerEntity;
import org.tihor.enums.SearchOperationType;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.mapper.CustomerMapper;
import org.tihor.mapper.PropertyMapper;
import org.tihor.model.request.CustomerRequest;
import org.tihor.model.request.FilterRequest;
import org.tihor.model.response.CustomerPropertyResponse;
import org.tihor.model.response.CustomerResponse;
import org.tihor.repository.CustomerRepository;
import org.tihor.specification.CustomerSpecification;

import java.util.List;

/**
 * The type Customer service.
 */
@Service
@RequiredArgsConstructor
public class CustomerService {
    /**
     * The Customer repository.
     */
    private final CustomerRepository customerRepository;

    /**
     * The Customer mapper.
     */
    private final CustomerMapper customerMapper;

    /**
     * The Property mapper.
     */
    private final PropertyMapper propertyMapper;

    /**
     * Create customer customer entity.
     *
     * @param request the request
     * @return the customer entity
     */
    public CustomerEntity createCustomer(final CustomerRequest request) {
        var entity = customerMapper.mapRequestToEntity(request);
        return customerRepository.save(entity);
    }

    /**
     * Gets customers.
     *
     * @return the customers
     */
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findByIsDeleted(false)
                .stream()
                .map(customerMapper::mapEntityToResponse)
                .toList();
    }

    /**
     * Gets customer details.
     *
     * @param id the id
     * @return the customer details
     */
    public CustomerPropertyResponse getCustomerDetails(final Long id) {
        var entity = customerRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        var customerResponse = customerMapper.mapEntityToResponse(entity);
        var propertyResponseList = propertyMapper.mapEntitiesToResponses(entity.getPropertyEntities());

        return CustomerPropertyResponse.builder()
                .customerInfo(customerResponse)
                .propertyInfo(propertyResponseList)
                .build();
    }

    /**
     * Search customers list.
     *
     * @param list the list
     * @return the list
     */
    public List<CustomerResponse> searchCustomers(final List<FilterRequest> list) {
        if (list == null || list.isEmpty()) {
            return getCustomers();
        }

        list.add(new FilterRequest("isDeleted", false, SearchOperationType.EQUAL));
        CustomerSpecification specification = new CustomerSpecification(list);
        var entities = customerRepository.findAll(specification);
        return entities.stream()
                .map(customerMapper::mapEntityToResponse)
                .toList();
    }

    /**
     * Update customer customer entity.
     *
     * @param id      the id
     * @param request the request
     * @return the customer entity
     */
    public CustomerEntity updateCustomer(final Long id, final CustomerRequest request) {
        var entity = customerRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        entity = customerMapper.mapRequestToEntity(request, entity);
        return customerRepository.save(entity);
    }

    /**
     * Delete customer string.
     *
     * @param id the id
     * @return the string
     */
    public String deleteCustomer(final Long id) {
        var entity = customerRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        entity.setIsDeleted(true);
        customerRepository.save(entity);
        return "Customer deleted successfully";
    }
}
