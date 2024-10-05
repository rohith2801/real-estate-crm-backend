package org.tihor.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tihor.entity.CustomerEntity;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.mapper.CustomerMapper;
import org.tihor.model.request.CustomerRequest;
import org.tihor.model.request.FilterRequest;
import org.tihor.model.response.CustomerResponse;
import org.tihor.repository.CustomerRepository;
import org.tihor.specification.CustomerSpecification;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * The type Customer service.
 */
@Service
public class CustomerService {
    /**
     * The Customer repository.
     */
    @Resource
    private CustomerRepository customerRepository;

    /**
     * The Customer mapper.
     */
    @Autowired
    private CustomerMapper customerMapper;

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
        var entities = customerRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(customerMapper::mapEntityToResponse)
                .toList();
    }

    /**
     * Gets customer details.
     *
     * @param id the id
     * @return the customer details
     */
    public CustomerResponse getCustomerDetails(final Long id) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        return customerMapper.mapEntityToResponse(entity, true);
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

        CustomerSpecification specification = new CustomerSpecification(list);
        var entities = customerRepository.findAll(specification);
        return entities.stream()
                .map(customerMapper::mapEntityToResponse)
                .toList();
    }

    public CustomerEntity updateCustomer(final Long id, final CustomerRequest request) {
        var entity = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        entity = customerMapper.mapRequestToEntity(request, entity);
        return customerRepository.save(entity);
    }
}
