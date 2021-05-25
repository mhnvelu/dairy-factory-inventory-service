package com.spring.microservices.dairyfactoryinventoryservice.services;

import com.spring.microservices.model.v2.ButterDtoV2;
import com.spring.microservices.model.events.NewInventoryEvent;
import com.spring.microservices.dairyfactoryinventoryservice.config.JmsConfig;
import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory;
import com.spring.microservices.dairyfactoryinventoryservice.repositories.ButterInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewInventoryEventListener {

    private final ButterInventoryRepository butterInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listenNewInventoryEvent(NewInventoryEvent newInventoryEvent) {
        ButterDtoV2 butterDtoV2 = newInventoryEvent.getButterDtoV2();
        log.info("Received NewInventoryEvent for butter : " + butterDtoV2.getName());
        butterInventoryRepository.save(ButterInventory.builder().butterId(butterDtoV2.getId()).upc(butterDtoV2.getUpc())
                                               .quantityOnHand(butterDtoV2.getQuantityOnHand()).build());
    }
}
