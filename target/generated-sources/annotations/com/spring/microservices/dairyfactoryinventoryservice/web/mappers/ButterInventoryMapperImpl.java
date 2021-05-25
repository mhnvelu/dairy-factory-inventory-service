package com.spring.microservices.dairyfactoryinventoryservice.web.mappers;

import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory;
import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory.ButterInventoryBuilder;
import com.spring.microservices.model.inventory.ButterInventoryDto;
import com.spring.microservices.model.inventory.ButterInventoryDto.ButterInventoryDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-25T14:49:55+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.1 (AdoptOpenJDK)"
)
@Component
public class ButterInventoryMapperImpl implements ButterInventoryMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public ButterInventory butterInventoryDtoToButterInventory(ButterInventoryDto butterInventoryDto) {
        if ( butterInventoryDto == null ) {
            return null;
        }

        ButterInventoryBuilder butterInventory = ButterInventory.builder();

        butterInventory.id( butterInventoryDto.getId() );
        butterInventory.createdDate( dateMapper.asTimestamp( butterInventoryDto.getCreatedDate() ) );
        butterInventory.lastModifiedDate( dateMapper.asTimestamp( butterInventoryDto.getLastModifiedDate() ) );
        butterInventory.butterId( butterInventoryDto.getButterId() );
        butterInventory.quantityOnHand( butterInventoryDto.getQuantityOnHand() );

        return butterInventory.build();
    }

    @Override
    public ButterInventoryDto butterInventoryToButterInventoryDto(ButterInventory butterInventory) {
        if ( butterInventory == null ) {
            return null;
        }

        ButterInventoryDtoBuilder butterInventoryDto = ButterInventoryDto.builder();

        butterInventoryDto.id( butterInventory.getId() );
        butterInventoryDto.createdDate( dateMapper.asOffsetDateTime( butterInventory.getCreatedDate() ) );
        butterInventoryDto.lastModifiedDate( dateMapper.asOffsetDateTime( butterInventory.getLastModifiedDate() ) );
        butterInventoryDto.butterId( butterInventory.getButterId() );
        butterInventoryDto.quantityOnHand( butterInventory.getQuantityOnHand() );

        return butterInventoryDto.build();
    }
}
