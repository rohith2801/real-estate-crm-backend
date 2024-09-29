package org.tihor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tihor.enums.LendingPartnerType;

import java.io.Serializable;

/**
 * The type Lending partner entity.
 */
@Entity(name = "lending_partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LendingPartnerEntity implements Serializable {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Name.
     */
    private String name;

    /**
     * The Type.
     */
    @Enumerated(EnumType.STRING)
    private LendingPartnerType type;

    /**
     * The Address.
     */
    @Column(length = 4000)
    private String address;
}
