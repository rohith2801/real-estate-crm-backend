package org.tihor.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tihor.entity.UserEntity;
import org.tihor.enums.SearchOperationType;
import org.tihor.enums.UserType;
import org.tihor.exception.InvalidRequestException;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.mapper.PropertyMapper;
import org.tihor.mapper.UserMapper;
import org.tihor.model.request.ChangePasswordRequest;
import org.tihor.model.request.ForgotPasswordRequest;
import org.tihor.model.request.UserRequest;
import org.tihor.model.request.FilterRequest;
import org.tihor.model.response.CustomerPropertyResponse;
import org.tihor.model.response.CustomerResponse;
import org.tihor.repository.UserRepository;
import org.tihor.specification.UserSpecification;

import java.util.List;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    /**
     * The User repository.
     */
    private final UserRepository userRepository;

    /**
     * The Password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The User mapper.
     */
    private final UserMapper userMapper;

    /**
     * The Property mapper.
     */
    private final PropertyMapper propertyMapper;

    /**
     * The Static otp.
     */
    @Value("${user.forgot-password.otp:9292}")
    private String staticOtp;

    /**
     * Create user user entity.
     *
     * @param request  the request
     * @param userType the user type
     * @return the user entity
     */
    public UserEntity createUser(final UserRequest request, final UserType userType) {
        var userEntity = userMapper.mapRequestToEntity(request, userType);
        return userRepository.save(userEntity);
    }

    /**
     * Update user user entity.
     *
     * @param id      the id
     * @param request the request
     * @return the user entity
     */
    public UserEntity updateUser(final Long id, final UserRequest request) {
        var userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
        userEntity = userMapper.mapRequestToEntity(request, userEntity);
        return userRepository.save(userEntity);
    }

    /**
     * Delete user string.
     *
     * @param id the id
     * @return the string
     */
    public String deleteUser(final Long id) {
        var entity = userRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
        entity.setIsDeleted(true);
        userRepository.save(entity);
        return "User deleted successfully";
    }

    /**
     * Gets customers.
     *
     * @return the customers
     */
    public List<CustomerResponse> getCustomers() {
        return userRepository.findByUserTypeAndIsDeleted(UserType.CUSTOMER, false)
                .stream()
                .map(userMapper::mapEntityToResponse)
                .toList();
    }

    /**
     * Gets customer details.
     *
     * @param id the id
     * @return the customer details
     */
    public CustomerPropertyResponse getCustomerDetails(final Long id) {
        var entity = userRepository.findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + id));
        var customerResponse = userMapper.mapEntityToResponse(entity);
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
        list.add(new FilterRequest("userType", UserType.CUSTOMER, SearchOperationType.EQUAL));

        UserSpecification specification = new UserSpecification(list);

        var entities = userRepository.findAll(specification);
        return entities.stream()
                .map(userMapper::mapEntityToResponse)
                .toList();
    }

    /**
     * Forgot password string.
     *
     * @param request the request
     * @return the string
     */
    public String forgotPassword(final ForgotPasswordRequest request) {
        if (!StringUtils.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new InvalidRequestException("Password and confirm password does not match");
        }

        var entity = userRepository.findByUsernameAndIsDeleted(request.getUsername(), false)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!validateOtp(request)) {
            throw new InvalidRequestException("Invalid Otp");
        }

        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(entity);

        return "Password updated successfully";
    }

    /**
     * Change password string.
     *
     * @param request the request
     * @return the string
     */
    public String changePassword(final ChangePasswordRequest request) {
        if (!StringUtils.equals(request.getNewPassword(), request.getConfirmNewPassword())) {
            throw new InvalidRequestException("Password and confirm password does not match");
        }

        var entity = userRepository.findByIdAndIsDeleted(request.getId(), false)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean isCurrentPasswordMatched = passwordEncoder.matches(request.getCurrentPassword(), entity.getPassword());
        if (!isCurrentPasswordMatched) {
            throw new InvalidRequestException("Invalid password");
        }

        entity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(entity);

        return "Password updated successfully";
    }

    /**
     * Validate otp boolean.
     *
     * @param request the request
     * @return the boolean
     */
    private boolean validateOtp(final ForgotPasswordRequest request) {
        return StringUtils.equals(request.getOtp(), staticOtp);
    }
}
