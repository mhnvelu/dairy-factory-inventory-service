package com.spring.microservices.dairyfactoryinventoryservice.bootstrap;

import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory;
import com.spring.microservices.dairyfactoryinventoryservice.repositories.ButterInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
public class ButterInventoryBootstrap implements CommandLineRunner {
    public static final String BUTTER_1_UPC = "0631234200036";
    public static final String BUTTER_2_UPC = "0631234300019";
    public static final String BUTTER_3_UPC = "0083783375213";
    public static final UUID BUTTER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BUTTER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BUTTER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final ButterInventoryRepository butterInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (butterInventoryRepository.count() == 0) {
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        butterInventoryRepository.save(ButterInventory
                                               .builder()
                                               .butterId(BUTTER_1_UUID)
                                               .upc(BUTTER_1_UPC)
                                               .quantityOnHand(50)
                                               .build());

        butterInventoryRepository.save(ButterInventory
                                               .builder()
                                               .butterId(BUTTER_2_UUID)
                                               .upc(BUTTER_2_UPC)
                                               .quantityOnHand(25)
                                               .build());

        butterInventoryRepository.saveAndFlush(ButterInventory
                                                       .builder()
                                                       .butterId(BUTTER_3_UUID)
                                                       .upc(BUTTER_3_UPC)
                                                       .quantityOnHand(75)
                                                       .build());

        log.debug("Loaded Inventory. Record count: " + butterInventoryRepository.count());
    }
}
