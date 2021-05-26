package com.spring.microservices.dairyfactoryinventoryservice.services;

import com.spring.microservices.model.ButterOrderDto;

public interface ButterOrderAllocationService {

    boolean allocateOrder(ButterOrderDto butterOrderDto);
}
