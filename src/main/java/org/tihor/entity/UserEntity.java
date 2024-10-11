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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.tihor.enums.UserType;

import java.util.Collection;
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
public class UserEntity implements UserDetails {
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

    /**
     * The Property entities.
     */
    @OneToMany(mappedBy = "userEntity")
    private List<PropertyEntity> propertyEntities;

    /**
     * Gets authorities.
     *
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleEntity.getName()));
    }
}
