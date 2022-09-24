package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.SaleLineDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleLineValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param SaleLineDto saleLineDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(SaleLineDto saleLineDto) {
        List<String> errors = new ArrayList<>();

        if (saleLineDto == null) {
            errors.add("Sale cannot be null");
            errors.add("Sale cannot be null");
        }
        if (saleLineDto.getArticle() == null) {
            errors.add("Article cannot be null");
        }
        if (saleLineDto.getSale() == null) {
            errors.add("Sale cannot be null");
        }

        return errors;
    }
}
