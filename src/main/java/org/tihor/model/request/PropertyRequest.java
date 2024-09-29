package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;
import org.tihor.enums.PropertyType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Property request.
 */
@Getter
@Setter
public class PropertyRequest implements Serializable {
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
     * The Apartment number.
     */
    private String apartmentNumber;

    /**
     * The Size.
     */
    private String size;

    /**
     * The Type.
     */
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
     * The Lending partners.
     */
    private List<PropertyLendingPartnerRequest> lendingPartners;
}
