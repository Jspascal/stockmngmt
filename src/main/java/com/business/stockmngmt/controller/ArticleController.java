package com.business.stockmngmt.controller;

import com.business.stockmngmt.controller.api.ArticleApi;
import com.business.stockmngmt.dto.ArticleDto;
import com.business.stockmngmt.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * @param articleDto
     * @return
     */
    @Override
    public ArticleDto save(ArticleDto articleDto) {

        return articleService.save(articleDto);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ArticleDto findById(Integer id) {

        return articleService.findById(id);
    }

    /**
     * @param codeArticle
     * @return
     */
    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        return articleService.findByCodeArticle(codeArticle);
    }

    /**
     * @return
     */
    @Override
    public List<ArticleDto> findAll() {

        return articleService.findAll();
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {

        articleService.delete(id);
    }
}
