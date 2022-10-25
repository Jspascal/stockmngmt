package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.SaleOrderLineDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.SaleOrderLine;
import com.business.stockmngmt.repository.SaleOrderLineRepository;
import com.business.stockmngmt.services.SaleOrderLineService;
import com.business.stockmngmt.validator.SaleOrderLineValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SaleOrderLineServiceImpl implements SaleOrderLineService {

    private SaleOrderLineRepository saleOrderLineRepository;

    @Autowired
    public SaleOrderLineServiceImpl(SaleOrderLineRepository saleOrderLineRepository) {
        this.saleOrderLineRepository = saleOrderLineRepository;
    }

    /**
     * @param saleOrderLineDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return SaleOrderLineDto
     */
    @Override
    public SaleOrderLineDto save(SaleOrderLineDto saleOrderLineDto) {
        List<String> errors = SaleOrderLineValidator.validate(saleOrderLineDto);
        if(!errors.isEmpty()) {
            log.error("SaleOrderLine is not valid {}", saleOrderLineDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return SaleOrderLineDto.fromEntity(
                saleOrderLineRepository.save(
                        SaleOrderLineDto.toEntity(saleOrderLineDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return SaleOrderLineDto
     */
    @Override
    public SaleOrderLineDto findById(Integer id) {
        if (id == null) {
            log.error("SaleOrderLine ID is null");
            return null;
        }

        Optional<SaleOrderLine> saleOrderLine = saleOrderLineRepository.findById(id);

        return Optional.of(SaleOrderLineDto.fromEntity(saleOrderLine.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND
                )
        );
    }

    /**
     * It takes a list of SaleOrderLine entities, maps them to SaleOrderLineDto objects, and returns a
     * list of SaleOrderLineDto objects
     *
     * @return A list of SaleOrderLineDto objects.
     */
    @Override
    public List<SaleOrderLineDto> findAll() {
        return saleOrderLineRepository.findAll().stream()
            .map(SaleOrderLineDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        saleOrderLineRepository.deleteById(id);
    }

}
