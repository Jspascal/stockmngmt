package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.SaleOrderDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.SaleOrder;
import com.business.stockmngmt.repository.SaleOrderRepository;
import com.business.stockmngmt.services.SaleOrderService;
import com.business.stockmngmt.validator.SaleOrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SaleOrderServiceImpl implements SaleOrderService {

    private SaleOrderRepository saleOrderRepository;

    @Autowired
    public SaleOrderServiceImpl(SaleOrderRepository saleOrderRepository) {
        this.saleOrderRepository = saleOrderRepository;
    }

    /**
     * @param saleOrderDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return SaleOrderDto
     */
    @Override
    public SaleOrderDto save(SaleOrderDto saleOrderDto) {
        List<String> errors = SaleOrderValidator.validate(saleOrderDto);
        if(!errors.isEmpty()) {
            log.error("SaleOrder is not valid {}", saleOrderDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return SaleOrderDto.fromEntity(
                saleOrderRepository.save(
                        SaleOrderDto.toEntity(saleOrderDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return SaleOrderDto
     */
    @Override
    public SaleOrderDto findById(Integer id) {
        if (id == null) {
            log.error("SaleOrder ID is null");
            return null;
        }

        Optional<SaleOrder> saleOrder = saleOrderRepository.findById(id);

        return Optional.of(SaleOrderDto.fromEntity(saleOrder.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND
                )
        );
    }

    @Override
    public List<SaleOrderDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        saleOrderRepository.deleteById(id);
    }

}
