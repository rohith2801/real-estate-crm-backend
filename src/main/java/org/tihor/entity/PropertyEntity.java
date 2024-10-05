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
import org.tihor.enums.PropertyType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Property entity.
 */
@Entity(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyEntity implements Serializable {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Customer entity.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    /**
     * The Property number.
     */
    private String propertyNumber;

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
     * The Size.
     */
    private String size;

    /**
     * The Type.
     */
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    /**
     * The Is ready to sell.
     */
    private Boolean isReadyToSell;

    /**
     * The Quoted price.
     */
    private Double quotedPrice;

    /**
     * The Is sold.
     */
    private Boolean isSold;

    /**
     * The Sold price.
     */
    private Double soldPrice;

    /**
     * The Sold date.
     */
    private LocalDate soldDate;

    /**
     * The Created date.
     */
    private LocalDate createdDate;

    /**
     * The Property lending partner entities.
     */
    @OneToMany(mappedBy = "propertyEntity")
    private List<PropertyLendingPartnerEntity> propertyLendingPartnerEntities;
}
