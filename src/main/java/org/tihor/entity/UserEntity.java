package org.tihor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tihor.enums.UserType;

import java.io.Serializable;
import java.util.List;

/**
 * The type User entity.
 */
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEntity implements Serializable {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */
    private String lastName;

    /**
     * The Street.
     */
    private String street;

    /**
     * The Pin code.
     */
    private String pinCode;

    /**
     * The Location.
     */
    private String location;

    /**
     * The Cell phone.
     */
    private String cellPhone;

    /**
     * The Email id.
     */
    private String emailId;

    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;

    /**
     * The Role entity.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    /**
     * The User type.
     */
    @Enumerated(EnumType.STRING)
    private UserType userType;

    /**
     * The Is deleted.
     */
    private Boolean isDeleted;

    @OneToMany(mappedBy = "userEntity")
    private List<PropertyEntity> propertyEntities;
}
