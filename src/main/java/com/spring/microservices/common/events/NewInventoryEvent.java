package com.spring.microservices.common.events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends ButterEvent {
    public NewInventoryEvent(ButterDtoV2 butterDtoV2) {
        super(butterDtoV2);
    }
}
