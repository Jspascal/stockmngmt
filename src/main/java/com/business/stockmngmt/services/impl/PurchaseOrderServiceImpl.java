package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.PurchaseOrderDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.PurchaseOrder;
import com.business.stockmngmt.repository.PurchaseOrderRepository;
import com.business.stockmngmt.services.PurchaseOrderService;
import com.business.stockmngmt.validator.PurchaseOrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    /**
     * @param purchaseOrderDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return PurchaseOrderDto
     */
    @Override
    public PurchaseOrderDto save(PurchaseOrderDto purchaseOrderDto) {
        List<String> errors = PurchaseOrderValidator.validate(purchaseOrderDto);
        if(!errors.isEmpty()) {
            log.error("PurchaseOrder is not valid {}", purchaseOrderDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return PurchaseOrderDto.fromEntity(
                purchaseOrderRepository.save(
                        PurchaseOrderDto.toEntity(purchaseOrderDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return PurchaseOrderDto
     */
    @Override
    public PurchaseOrderDto findById(Integer id) {
        if (id == null) {
            log.error("PurchaseOrder ID is null");
            return null;
        }

        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);

        return Optional.of(PurchaseOrderDto.fromEntity(purchaseOrder.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND
                )
        );
    }

    @Override
    public List<PurchaseOrderDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        purchaseOrderRepository.deleteById(id);
    }

}
