package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.ArticleDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Article;
import com.business.stockmngmt.repository.ArticleRepository;
import com.business.stockmngmt.services.ArticleService;
import com.business.stockmngmt.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    // It's a constructor injection.
    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * It validates the article, if it's not valid, it throws an exception
     *
     * @param articleDto The object that is being validated.
     * @return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
     */
    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()) {
            log.error("Article is not valid {}", articleDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        return ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(articleDto)
                )
        );
    }

    /**
     * It returns an ArticleDto object from the database, or throws an exception if the article is not
     * found
     *
     * @param id The ID of the article to be found
     * @return ArticleDto
     */
    @Override
    public ArticleDto findById(Integer id) {
        if(id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    /**
     * It returns an ArticleDto object from the database, if the article exists, otherwise it throws an
     * exception
     *
     * @param codeArticle String
     * @return ArticleDto
     */
    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength(codeArticle)){
            log.error("Article CODE is null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the CODE" + codeArticle + "inside the database",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    /**
     * It takes a list of Article entities, converts them to ArticleDto objects, and returns a list of
     * ArticleDto objects
     *
     * @return A list of ArticleDto objects.
     */
    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * The function deletes an article from the database by its ID
     *
     * @param id The id of the article to be deleted.
     */
    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
