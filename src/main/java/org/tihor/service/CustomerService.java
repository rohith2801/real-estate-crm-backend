package org.tihor.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tihor.entity.CustomerEntity;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.mapper.CustomerMapper;
import org.tihor.model.request.CustomerRequest;
import org.tihor.model.response.CustomerResponse;
import org.tihor.repository.CustomerRepository;

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
        entity = customerRepository.save(entity);
        return entity;
    }

    /**
     * Gets customers.
     *
     * @return the customers
     */
    public List<CustomerResponse> getCustomers() {
        var entities = customerRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(entity -> customerMapper.mapEntityToResponse(entity))
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
}
