package com.spring.microservices.dairyfactoryinventoryservice.services.listeners;

import com.spring.microservices.dairyfactoryinventoryservice.config.JmsConfig;
import com.spring.microservices.dairyfactoryinventoryservice.services.ButterOrderAllocationService;
import com.spring.microservices.model.events.DeAllocateButterOrderRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeAllocateButterOrderRequestEventListener {
    private final ButterOrderAllocationService butterOrderAllocationService;

    @JmsListener(destination = JmsConfig.DE_ALLOCATE_ORDER_QUEUE)
    public void listenDeAllocateButterOrderRequestEvent(DeAllocateButterOrderRequestEvent deAllocateButterOrderRequestEvent) {
        String orderId = deAllocateButterOrderRequestEvent.getButterOrderDto().getId().toString();
        log.info("Received DeAllocateButterOrderRequestEvent for order : " + orderId);

        butterOrderAllocationService.deAllocateOrder(deAllocateButterOrderRequestEvent.getButterOrderDto());
    }
}
