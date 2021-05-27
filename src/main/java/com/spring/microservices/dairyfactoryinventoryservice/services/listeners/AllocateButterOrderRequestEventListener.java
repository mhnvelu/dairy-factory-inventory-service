package com.spring.microservices.dairyfactoryinventoryservice.services.listeners;

import com.spring.microservices.dairyfactoryinventoryservice.config.JmsConfig;
import com.spring.microservices.dairyfactoryinventoryservice.services.ButterOrderAllocationService;
import com.spring.microservices.model.events.AllocateButterOrderRequestEvent;
import com.spring.microservices.model.events.AllocateButterOrderResponseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateButterOrderRequestEventListener {

    private final ButterOrderAllocationService butterOrderAllocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_REQUEST_QUEUE)
    public void listenAllocateButterOrderRequestEvent(AllocateButterOrderRequestEvent allocateButterOrderRequestEvent) {
        String orderId = allocateButterOrderRequestEvent.getButterOrderDto().getId().toString();
        log.info("Received AllocateButterOrderRequestEvent for order : " + orderId);

        AllocateButterOrderResponseEvent.AllocateButterOrderResponseEventBuilder allocateButterOrderResponseEventBuilder =
                AllocateButterOrderResponseEvent.builder().butterOrderDto(allocateButterOrderRequestEvent.getButterOrderDto());

        try {
            boolean result = butterOrderAllocationService.allocateOrder(allocateButterOrderRequestEvent.getButterOrderDto());
            if (result) {
                allocateButterOrderResponseEventBuilder.pendingInventory(true);
            } else {
                allocateButterOrderResponseEventBuilder.pendingInventory(false);
            }
        } catch (Exception e) {
            log.error("Allocation failed for order id : " + orderId);
            allocateButterOrderResponseEventBuilder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, allocateButterOrderResponseEventBuilder.build());

    }
}
