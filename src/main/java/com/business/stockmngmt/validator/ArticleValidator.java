package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.ArticleDto;
import com.business.stockmngmt.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param ArticleDto articleDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if (articleDto == null) {
            errors.add("Please enter article code");
            errors.add("Please enter article label");
            errors.add("Please enter article net price");
            errors.add("Please enter VAT rate");
            errors.add("Please select a category");
        }
        if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
            errors.add("Please enter article code");
        }
        if (!StringUtils.hasLength(articleDto.getLabelArticle())) {
            errors.add("Please enter article label");
        }
        if (articleDto.getNetprice() == null) {
            errors.add("Please enter article net price");
        }
        if (articleDto.getVatRate() == null) {
            errors.add("Please enter VAT rate");
        }
        if (articleDto.getCategory() == null) {
            errors.add("Please select a category");
        }

        return errors;
    }
}
