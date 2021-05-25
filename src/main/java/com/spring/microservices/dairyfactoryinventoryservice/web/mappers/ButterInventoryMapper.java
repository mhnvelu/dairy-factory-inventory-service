package com.spring.microservices.dairyfactoryinventoryservice.web.mappers;

import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory;
import com.spring.microservices.model.inventory.ButterInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ButterInventoryMapper {

    ButterInventory butterInventoryDtoToButterInventory(ButterInventoryDto butterInventoryDto);

    ButterInventoryDto butterInventoryToButterInventoryDto(ButterInventory butterInventory);
}
