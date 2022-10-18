package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.StockMvtDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.StockMvt;
import com.business.stockmngmt.repository.StockMvtRepository;
import com.business.stockmngmt.services.StockMvtService;
import com.business.stockmngmt.validator.StockMvtValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
public class StockMvtServiceImpl implements StockMvtService {

    private StockMvtRepository stockMvtRepository;

    @Autowired
    public StockMvtServiceImpl(StockMvtRepository stockMvtRepository) {
        this.stockMvtRepository = stockMvtRepository;
    }

    /**
     * It validates the stockMvtDto, if it's not valid, it throws an exception
     *
     * @param stockMvtDto the object to be validated
     * @return StockMvtDto
     */
    @Override
    public StockMvtDto save(StockMvtDto stockMvtDto) {
        List<String> errors = StockMvtValidator.validate(stockMvtDto);
        if (!errors.isEmpty()) {
            log.error("StockMvt is not valid {}", stockMvtDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return StockMvtDto.fromEntity(
                stockMvtRepository.save(
                        StockMvtDto.toEntity(stockMvtDto)));
    }

    /**
     * It returns a StockMvtDto object if the ID is not null, otherwise it throws an
     * EntityNotFoundException
     *
     * @param id the id of the stock movement
     * @return A StockMvtDto object
     */
    @Override
    public StockMvtDto findById(Integer id) {
        if (id == null) {
            log.error("StockMvt ID is null");
            return null;
        }

        return stockMvtRepository.findById(id)
                .map(StockMvtDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND));
    }

    /**
     * This function takes a list of StockMvt entities, converts them to StockMvtDto objects, and
     * returns a list of StockMvtDto objects.
     *
     * @return A list of StockMvtDto objects.
     */
    @Override
    public List<StockMvtDto> findAll() {
        return stockMvtRepository.findAll().stream()
                .map(StockMvtDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * It deletes a stock movement by its ID
     *
     * @param id the id of the stock movement to delete
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        stockMvtRepository.deleteById(id);
    }

}
