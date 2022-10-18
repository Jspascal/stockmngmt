package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.SaleOrderDto;
import com.business.stockmngmt.dto.SaleOrderLineDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.Client;
import com.business.stockmngmt.model.SaleOrder;
import com.business.stockmngmt.model.SaleOrderLine;
import com.business.stockmngmt.repository.ArticleRepository;
import com.business.stockmngmt.repository.ClientRepository;
import com.business.stockmngmt.repository.SaleOrderLineRepository;
import com.business.stockmngmt.repository.SaleOrderRepository;
import com.business.stockmngmt.services.SaleOrderService;
import com.business.stockmngmt.validator.SaleOrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SaleOrderServiceImpl implements SaleOrderService {

    private SaleOrderRepository saleOrderRepository;
    private ClientRepository clientRepository;
    private SaleOrderLineRepository saleOrderLineRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public SaleOrderServiceImpl(SaleOrderRepository saleOrderRepository, ClientRepository clientRepository, ArticleRepository articleRepository, SaleOrderLineRepository saleOrderLineRepository) {
        this.saleOrderRepository = saleOrderRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.saleOrderLineRepository = saleOrderLineRepository;
    }

    /**
     * It saves a sale order and its lines
     *
     * @param saleOrderDto the object that I want to save
     * @return A SaleOrderDto object.
     */
    @Override
    public SaleOrderDto save(SaleOrderDto saleOrderDto) {
        List<String> errors = SaleOrderValidator.validate(saleOrderDto);
        if(!errors.isEmpty()) {
            log.error("SaleOrder is not valid {}", saleOrderDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }

        // Checking if the client exists in the database.
        Optional<Client> client = clientRepository.findById(saleOrderDto.getClient().getId());
        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found in the Database.", saleOrderDto.getClient().getId());
            throw new EntityNotFoundException("No client with the ID " + saleOrderDto.getClient().getId() +" was found in the database", ErrorCodes.CLIENT_NOT_FOUND);
        }

        // Checking if the article exists in the database.
        List<String> articleErrors = new ArrayList<>();
        if (saleOrderDto.getSaleOrderLines() != null) {
            saleOrderDto.getSaleOrderLines().forEach(salOrdLine -> {
                if (salOrdLine.getArticle() != null)  {
                    Optional<Article> article = articleRepository.findById(salOrdLine.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("Article with id " + salOrdLine.getArticle().getId() + " doesn't exist.")
                    }
                }
                else {
                    articleErrors.add("Unable to save a command with void article.");
                }
            });

            if (!articleErrors.isEmpty()) {
                log.warn(" ");
                throw new InvalidEntityException("Article not found in the database", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
            }
        }

        SaleOrder saveSaleOrder = saleOrderRepository.save(SaleOrderDto.toEntity(saleOrderDto));

        // Saving the sale order lines.
        if (saleOrderDto.getSaleOrderLines() != null) {
            saleOrderDto.getSaleOrderLines().forEach(salOrdLine -> {
                SaleOrderLine saleOrderLine = SaleOrderLineDto.toEntity(salOrdLine);
                saleOrderLine.setSaleOrder(saveSaleOrder);
                saleOrderLineRepository.save(saleOrderLine);
            });
        }

        return SaleOrderDto.fromEntity(saveSaleOrder);
    }

    /**
     * It returns a SaleOrderDto object from the database, if the SaleOrderDto object is not found, it
     * throws an EntityNotFoundException
     *
     * @param id The ID of the sale order to be found
     * @return SaleOrderDto
     */
    @Override
    public SaleOrderDto findById(Integer id) {
        if (id == null) {
            log.error("SaleOrder ID is null");
            return null;
        }

        return saleOrderRepository.findById(id)
            .map(SaleOrderDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "No sale order was found with the ID " + id + " in the database."
            ));
    }

    @Override
    public SaleOrderDto findByRef(String ref) {
        if (ref == null) {
            log.error("SaleOrder ref is null");
            return null;
        }

        return saleOrderRepository.findByOrderRef(ref)
            .map(SaleOrderDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "No sale order was found with the ref " + ref + " in the database."
            ));
    }

    /**
     * It returns a list of SaleOrderDto objects.
     *
     * @return A list of SaleOrderDto objects.
     */
    @Override
    public List<SaleOrderDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * The function deletes a purchase order line by its ID
     *
     * @param id The ID of the purchase order line to delete.
     */
    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        saleOrderRepository.deleteById(id);
    }

}
