package org.tihor.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Property lending partner response.
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class PropertyLendingPartnerResponse implements Serializable {
    /**
     * The Id.
     */
    private Long id;

    /**
     * The Lending partner id.
     */
    private Long lendingPartnerId;

    /**
     * The Lending partner.
     */
    private LendingPartnerResponse lendingPartner;

    /**
     * The Loan amount.
     */
    private Double loanAmount;

    /**
     * The Rate of interest.
     */
    private Double rateOfInterest;

    /**
     * The Tenure in months.
     */
    private Integer tenureInMonths;

    /**
     * The Redemption.
     */
    private Double redemption;

    /**
     * The Rate.
     */
    private Double rate;

    /**
     * The Notes.
     */
    private String notes;
}
