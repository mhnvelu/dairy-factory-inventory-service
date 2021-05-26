package com.spring.microservices.dairyfactoryinventoryservice.services;

import com.spring.microservices.model.ButterOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ButterOrderAllocationServiceImpl implements  ButterOrderAllocationService{
    @Override
    public boolean allocateOrder(ButterOrderDto butterOrderDto) {
        return false;
    }
}
