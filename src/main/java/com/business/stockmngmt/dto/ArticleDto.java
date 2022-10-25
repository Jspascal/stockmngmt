package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.model.Category;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String labelArticle;

    private BigDecimal netprice;

    BigDecimal vatRate;

    private BigDecimal taxedPrice;

    String photoArticle;

    private Category category;

    private Integer idEntreprise;

    /*
     * function that allow us to map from Article to ArticleDto
     *
     * @param Article article
     * */
    public static ArticleDto fromEntity (Article article) {
        if (article == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.ARTICLE_NOT_FOUND);

        }

        return ArticleDto.builder()
                .codeArticle(article.getCodeArticle())
                .labelArticle(article.getLabelArticle())
                .netprice(article.getNetprice())
                .vatRate(article.getVatRate())
                .taxedPrice(article.getTaxedPrice())
                .photoArticle(article.getPhotoArticle())
                .category(article.getCategory())
                .idEntreprise(article.getIdEntreprise())
                .build();
    }

    /*
     * function that allow us to map from ArticleDto to Article
     *
     * @param Article articleDto
     * */
    public static Article toEntity (ArticleDto articleDto) {
        if (articleDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.ARTICLE_NOT_FOUND);

        }

        Article article = new Article();
        article.setCodeArticle(article.getCodeArticle());
        article.setLabelArticle(article.getLabelArticle());
        article.setPhotoArticle(article.getPhotoArticle());
        article.setNetprice(article.getNetprice());
        article.setVatRate(article.getVatRate());
        article.setTaxedPrice(article.getTaxedPrice());
        article.setCategory(article.getCategory());
        article.setIdEntreprise(article.getIdEntreprise());

        return article;
    }
}
