package org.tihor.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tihor.model.Response;
import org.tihor.service.CustomerService;

/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {
    /**
     * The Customer service.
     */
    private final CustomerService customerService;

    /**
     * Gets customers.
     *
     * @return the customers
     */
    @GetMapping
    public ResponseEntity<Response> getCustomers() {
        return ResponseEntity.ok(Response.withData(customerService.getCustomers()));
    }

    /**
     * Gets customer details by id.
     *
     * @param id the id
     * @return the customer details by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomerDetailsById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(Response.withData(customerService.getCustomerDetails(id)));
    }
}
