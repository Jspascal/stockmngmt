package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.SaleDto;
import com.business.stockmngmt.dto.SaleLineDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.Sale;
import com.business.stockmngmt.model.SaleLine;
import com.business.stockmngmt.repository.ArticleRepository;
import com.business.stockmngmt.repository.SaleLineRepository;
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
    private SaleLineRepository saleLineRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, SaleLineRepository saleLineRepository, ArticleRepository articleRepository) {
        this.saleRepository = saleRepository;
        this.saleLineRepository = saleLineRepository;
        this.articleRepository = articleRepository;
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
            throw new InvalidEntityException("Sale is not valid", ErrorCodes.SALE_NOT_FOUND, errors);
        }

        // Checking if the article exists in the database.
        List<String> articleErrors = new ArrayList<>();

        saleDto.getSaleLines().forEach(line -> {
            Optional<Article> article = articleRepository.findById(line.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Article with id " + line.getArticle().getId() + " doesn't exist.");
            }
        });

        if (!articleErrors.isEmpty()) {
            log.warn("Article not found in the database");
            throw new InvalidEntityException("article not found in the database", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        Sale saveSale = saleRepository.save(SaleDto.toEntity(saleDto));

        // Saving the sale order lines.
        if (saleDto.getSaleLines() != null) {
            saleDto.getSaleLines().forEach(line -> {
                SaleLine saleLine = SaleLineDto.toEntity(line);
                saleLine.setSale(saveSale);
                saleLineRepository.save(saleLine);
            });
        }

        return SaleDto.fromEntity(saveSale);

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

    @Override
    public SaleDto findByCode(String code) {
        if (code == null) {
            log.error("Sale code is null");
            return null;
        }

        return saleRepository.findSaleByCode(code)
            .map(SaleDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "No sale was found with the code " + code + " in the database."
            ));
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
