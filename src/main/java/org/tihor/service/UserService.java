package org.tihor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tihor.entity.UserEntity;
import org.tihor.enums.SearchOperationType;
import org.tihor.enums.UserType;
import org.tihor.exception.ResourceNotFoundException;
import org.tihor.mapper.PropertyMapper;
import org.tihor.mapper.UserMapper;
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
     * The User mapper.
     */
    private final UserMapper userMapper;

    /**
     * The Property mapper.
     */
    private final PropertyMapper propertyMapper;

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
}
