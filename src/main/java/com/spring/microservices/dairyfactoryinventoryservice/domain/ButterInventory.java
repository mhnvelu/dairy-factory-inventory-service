package com.spring.microservices.dairyfactoryinventoryservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ButterInventory extends BaseEntity {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID butterId;

    private String upc;
    private Integer quantityOnHand = 0;

    @Builder
    public ButterInventory(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, UUID butterId,
                           String upc, Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.butterId = butterId;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
    }
}
