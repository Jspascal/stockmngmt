package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.PurchaseOrderLineDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.PurchaseOrderLine;
import com.business.stockmngmt.repository.PurchaseOrderLineRepository;
import com.business.stockmngmt.services.PurchaseOrderLineService;
import com.business.stockmngmt.validator.PurchaseOrderLineValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
public class PurchaseOrderLineServiceImpl implements PurchaseOrderLineService {

    private PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Autowired
    public PurchaseOrderLineServiceImpl(PurchaseOrderLineRepository purchaseOrderLineRepository) {
        this.purchaseOrderLineRepository = purchaseOrderLineRepository;
    }

    /**
     * @param purchaseOrderLineDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return PurchaseOrderLineDto
     */
    @Override
    public PurchaseOrderLineDto save(PurchaseOrderLineDto purchaseOrderLineDto) {
        List<String> errors = PurchaseOrderLineValidator.validate(purchaseOrderLineDto);
        if(!errors.isEmpty()) {
            log.error("PurchaseOrderLine is not valid {}", purchaseOrderLineDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_LINE_NOT_VALID, errors);
        }
        return PurchaseOrderLineDto.fromEntity(
                purchaseOrderLineRepository.save(
                        PurchaseOrderLineDto.toEntity(purchaseOrderLineDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return PurchaseOrderLineDto
     */
    @Override
    public PurchaseOrderLineDto findById(Integer id) {
        if (id == null) {
            log.error("PurchaseOrderLine ID is null");
            return null;
        }

        Optional<PurchaseOrderLine> purchaseOrderLine = purchaseOrderLineRepository.findById(id);

        return Optional.of(PurchaseOrderLineDto.fromEntity(purchaseOrderLine.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_LINE_NOT_FOUND
                )
        );
    }

    /**
     * It takes a list of purchase order lines, maps them to a list of purchase order line DTOs, and
     * returns the list of purchase order line DTOs
     *
     * @return A list of PurchaseOrderLineDto objects.
     */
    @Override
    public List<PurchaseOrderLineDto> findAll() {
        return purchaseOrderLineRepository.findAll().stream()
            .map(PurchaseOrderLineDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        purchaseOrderLineRepository.deleteById(id);
    }

}
