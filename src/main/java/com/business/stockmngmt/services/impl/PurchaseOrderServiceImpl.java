package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.PurchaseOrderDto;
import com.business.stockmngmt.dto.PurchaseOrderLineDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.Provider;
import com.business.stockmngmt.model.PurchaseOrder;
import com.business.stockmngmt.model.PurchaseOrderLine;
import com.business.stockmngmt.repository.ArticleRepository;
import com.business.stockmngmt.repository.ProviderRepository;
import com.business.stockmngmt.repository.PurchaseOrderLineRepository;
import com.business.stockmngmt.repository.PurchaseOrderRepository;
import com.business.stockmngmt.services.PurchaseOrderService;
import com.business.stockmngmt.validator.PurchaseOrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private PurchaseOrderRepository purchaseOrderRepository;
    private ProviderRepository providerRepository;
    private PurchaseOrderLineRepository purchaseOrderLineRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, ProviderRepository providerRepository, PurchaseOrderLineRepository purchaseOrderLineRepository, ArticleRepository articleRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.providerRepository = providerRepository;
        this.purchaseOrderLineRepository = purchaseOrderLineRepository;
        this.articleRepository = articleRepository;
    }

    /**
     * It saves a purchase order and its lines
     *
     * @param purchaseOrderDto the object that is being saved
     * @return A PurchaseOrderDto object.
     */
    @Override
    public PurchaseOrderDto save(PurchaseOrderDto purchaseOrderDto) {
        List<String> errors = PurchaseOrderValidator.validate(purchaseOrderDto);
        if(!errors.isEmpty()) {
            log.error("Purchase Order is not valid {}", purchaseOrderDto);
            throw new InvalidEntityException("Purchase Order is not valid", ErrorCodes.PURCHASE_ORDER_NOT_FOUND, errors);
        }

        // Checking if the client exists in the database.
        Optional<Provider> provider = providerRepository.findById(purchaseOrderDto.getProvider().getId());
        if (provider.isEmpty()) {
            log.warn("Provider with ID {} was not found in the Database.", purchaseOrderDto.getProvider().getId());
            throw new EntityNotFoundException("No client with the ID " + purchaseOrderDto.getProvider().getId() +" was found in the database", ErrorCodes.PROVIDER_NOT_FOUND);
        }

        // Checking if the article exists in the database.
        List<String> articleErrors = new ArrayList<>();
        if (purchaseOrderDto.getPurchaseOrderLines() != null) {
            purchaseOrderDto.getPurchaseOrderLines().forEach(line -> {
                if (line.getArticle() != null)  {
                    Optional<Article> article = articleRepository.findById(line.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("Article with id " + line.getArticle().getId() + " doesn't exist.");
                    }
                }
                else {
                    articleErrors.add("Unable to save a command with void article.");
                }
            });

            if (!articleErrors.isEmpty()) {
                log.warn(" ");
                throw new InvalidEntityException("article not found in the database", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
            }
        }

        PurchaseOrder savePurchaseOrder = purchaseOrderRepository.save(PurchaseOrderDto.toEntity(purchaseOrderDto));

        // Saving the sale order lines.
        if (purchaseOrderDto.getPurchaseOrderLines() != null) {
            purchaseOrderDto.getPurchaseOrderLines().forEach(line -> {
                PurchaseOrderLine purchaseOrderLine = PurchaseOrderLineDto.toEntity(line);
                purchaseOrderLine.setPurchaseOrder(savePurchaseOrder);
                purchaseOrderLineRepository.save(purchaseOrderLine);
            });
        }

        return PurchaseOrderDto.fromEntity(savePurchaseOrder);
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

    /**
     * It returns a purchase order by code, or throws an exception if it doesn't exist
     *
     * @param code String
     * @return A PurchaseOrderDto object.
     */
    @Override
    public PurchaseOrderDto findByCode(String code) {
        if (code == null) {
            log.error("Purhcase Order code is null");
            return null;
        }

        return purchaseOrderRepository.findPurchaseOrderByCode(code)
            .map(PurchaseOrderDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "No purchase order was found with the code " + code + " in the database."
            ));
    }

    /**
     * It takes a list of purchase orders, maps them to a list of purchase order DTOs, and returns the
     * list of purchase order DTOs
     *
     * @return A list of PurchaseOrderDto objects.
     */
    @Override
    public List<PurchaseOrderDto> findAll() {
        return purchaseOrderRepository.findAll().stream()
            .map(PurchaseOrderDto::fromEntity)
            .collect(Collectors.toList());
    }

    /**
     * This function deletes a purchase order line from the database
     *
     * @param id The ID of the purchase order line to delete.
     */
    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        purchaseOrderRepository.deleteById(id);
    }

}
