package com.spring.microservices.dairyfactoryinventoryservice.web.controllers;

import com.spring.microservices.dairyfactoryinventoryservice.repositories.ButterInventoryRepository;
import com.spring.microservices.dairyfactoryinventoryservice.web.mappers.ButterInventoryMapper;
import com.spring.microservices.dairyfactoryinventoryservice.web.model.ButterInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ButterInventoryController {

    private final ButterInventoryRepository butterInventoryRepository;
    private final ButterInventoryMapper butterInventoryMapper;

    @GetMapping("api/v1/butter/{butterId}/inventory")
    List<ButterInventoryDto> listButtersById(@PathVariable UUID butterId) {
        log.debug("Finding Inventory for butterId:" + butterId);

        return butterInventoryRepository.findAllByButterId(butterId)
                .stream()
                .map(butterInventoryMapper::butterInventoryToButterInventoryDto)
                .collect(Collectors.toList());
    }
}
