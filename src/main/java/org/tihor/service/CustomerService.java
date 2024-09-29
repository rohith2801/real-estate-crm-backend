package org.tihor.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tihor.entity.CustomerEntity;
import org.tihor.mapper.CustomerMapper;
import org.tihor.model.request.CustomerRequest;
import org.tihor.repository.CustomerRepository;

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
}
