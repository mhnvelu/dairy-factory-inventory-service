package com.spring.microservices.dairyfactoryinventoryservice.services;

import com.spring.microservices.dairyfactoryinventoryservice.domain.ButterInventory;
import com.spring.microservices.dairyfactoryinventoryservice.repositories.ButterInventoryRepository;
import com.spring.microservices.model.ButterOrderDto;
import com.spring.microservices.model.ButterOrderLineDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ButterOrderAllocationServiceImpl implements ButterOrderAllocationService {

    @Autowired
    private ButterInventoryRepository butterInventoryRepository;

    @Override
    public boolean allocateOrder(ButterOrderDto butterOrderDto) {
        log.info("Allocating OrderId: " + butterOrderDto.getId());

        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        butterOrderDto.getButterOrderLines().forEach(butterOrderLine -> {
            if ((((butterOrderLine.getOrderQuantity() != null ? butterOrderLine.getOrderQuantity() : 0)
                  - (butterOrderLine.getQuantityAllocated() != null ? butterOrderLine.getQuantityAllocated() : 0)) > 0)) {
                allocateButterOrderLine(butterOrderLine);
            }
            totalOrdered.set(totalOrdered.get() + butterOrderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() +
                               (butterOrderLine.getQuantityAllocated() != null ? butterOrderLine.getQuantityAllocated() : 0));
        });

        log.info("Total Ordered: " + totalOrdered.get() + " Total Allocated: " + totalAllocated.get());

        return totalOrdered.get() == totalAllocated.get();
    }

    private void allocateButterOrderLine(ButterOrderLineDto butterOrderLine) {
        List<ButterInventory> butterInventoryList = butterInventoryRepository.findAllByUpc(butterOrderLine.getUpc());

        butterInventoryList.forEach(butterInventory -> {
            int inventory = (butterInventory.getQuantityOnHand() == null) ? 0 : butterInventory.getQuantityOnHand();
            int orderQty = (butterOrderLine.getOrderQuantity() == null) ? 0 : butterOrderLine.getOrderQuantity();
            int allocatedQty = (butterOrderLine.getQuantityAllocated() == null) ? 0 : butterOrderLine.getQuantityAllocated();
            int qtyToAllocate = orderQty - allocatedQty;

            if (inventory >= qtyToAllocate) { // full allocation
                inventory = inventory - qtyToAllocate;
                butterOrderLine.setQuantityAllocated(orderQty);
                butterInventory.setQuantityOnHand(inventory);

                butterInventoryRepository.save(butterInventory);
            } else if (inventory > 0) { //partial allocation
                butterOrderLine.setQuantityAllocated(allocatedQty + inventory);
                butterInventory.setQuantityOnHand(0);

            }

            if (butterInventory.getQuantityOnHand() == 0) {
                butterInventoryRepository.delete(butterInventory);
            }
        });

    }
}
