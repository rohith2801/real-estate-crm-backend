package org.tihor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Property lending partner request.
 */
@Getter
@Setter
public class PropertyLendingPartnerRequest implements Serializable {
    /**
     * The Lending partner id.
     */
    private Long lendingPartnerId;

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
