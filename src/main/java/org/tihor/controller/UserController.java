package org.tihor.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tihor.model.Response;
import org.tihor.model.request.ChangePasswordRequest;
import org.tihor.model.request.FilterRequest;
import org.tihor.model.request.ForgotPasswordRequest;
import org.tihor.service.UserService;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    /**
     * The User service.
     */
    private final UserService userService;

    /**
     * Gets customers.
     *
     * @return the customers
     */
    @GetMapping("/customers")
    public ResponseEntity<Response> getCustomers() {
        return ResponseEntity.ok(Response.withData(userService.getCustomers()));
    }

    /**
     * Gets customer details by id.
     *
     * @param id the id
     * @return the customer details by id
     */
    @GetMapping("/{id}/customer-details")
    public ResponseEntity<Response> getCustomerDetailsById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(Response.withData(userService.getCustomerDetails(id)));
    }

    /**
     * Search api response entity.
     *
     * @param requests the requests
     * @return the response entity
     */
    @PostMapping("/customer/search")
    public ResponseEntity<Response> searchApi(@RequestBody @Valid final List<FilterRequest> requests) {
        return ResponseEntity.ok(Response.withData(userService.searchCustomers(requests)));
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(Response.withData(userService.deleteUser(id)));
    }

    /**
     * Forgot password response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<Response> forgotPassword(@RequestBody @Valid final ForgotPasswordRequest request) {
        return ResponseEntity.ok(Response.withData(userService.forgotPassword(request)));
    }

    /**
     * Change password response entity.
     *
     * @param id      the id
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/{id}/change-password")
    public ResponseEntity<Response> changePassword(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final ChangePasswordRequest request
    ) {
        return ResponseEntity.ok(Response.withData(userService.changePassword(id, request)));
    }
}
