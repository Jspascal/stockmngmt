package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.SaleDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Sale;
import com.business.stockmngmt.repository.SaleRepository;
import com.business.stockmngmt.services.SaleService;
import com.business.stockmngmt.validator.SaleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    /**
     * @param saleDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return SaleDto
     */
    @Override
    public SaleDto save(SaleDto saleDto) {
        List<String> errors = SaleValidator.validate(saleDto);
        if(!errors.isEmpty()) {
            log.error("Sale is not valid {}", saleDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return SaleDto.fromEntity(
                saleRepository.save(
                        SaleDto.toEntity(saleDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return SaleDto
     */
    @Override
    public SaleDto findById(Integer id) {
        if (id == null) {
            log.error("Sale ID is null");
            return null;
        }

        Optional<Sale> sale = saleRepository.findById(id);

        return Optional.of(SaleDto.fromEntity(sale.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND
                )
        );
    }

    /**
     * It takes a list of Sale entities, maps them to SaleDto objects, and returns a list of SaleDto
     * objects
     *
     * @return A list of SaleDto objects.
     */
    @Override
    public List<SaleDto> findAll() {
        return saleRepository.findAll().stream()
            .map(SaleDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        saleRepository.deleteById(id);
    }

}
