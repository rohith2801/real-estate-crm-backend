package org.tihor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Property lending partner entity.
 */
@Entity(name = "property_lending_partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyLendingPartnerEntity implements Serializable {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Property entity.
     */
    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity propertyEntity;

    /**
     * The Lending partner entity.
     */
    @ManyToOne
    @JoinColumn(name = "lending_partner_id")
    private LendingPartnerEntity lendingPartnerEntity;

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
    @Column(length = 4000)
    private String notes;
}
