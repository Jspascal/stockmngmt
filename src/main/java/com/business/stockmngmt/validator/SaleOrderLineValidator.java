package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.SaleOrderLineDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderLineValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param SaleOrderLineDto saleOrderLineDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(SaleOrderLineDto saleOrderLineDto) {
        List<String> errors = new ArrayList<>();

        if (saleOrderLineDto == null) {
            errors.add("Article cannot be null");
            errors.add("Sale Order cannot be null");
        }
        if (saleOrderLineDto.getArticle() == null) {
            errors.add("Article cannot be null");
        }
        if (saleOrderLineDto.getSaleOrder() == null) {
            errors.add("Sale Order cannot be null");
        }

        return errors;
    }
}
