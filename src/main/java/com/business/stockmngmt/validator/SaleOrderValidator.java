package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.SaleOrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleOrderValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param SaleOrderDto saleOrderDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(SaleOrderDto saleOrderDto) {
        List<String> errors = new ArrayList<>();

        if (saleOrderDto == null) {
            errors.add("Please enter saleOrder reference");
            errors.add("Please enter saleOrder date");
            errors.add("Please select saleOrder client");
        }
        if (!StringUtils.hasLength(saleOrderDto.getOrderRef())) {
            errors.add("Please enter saleOrder reference");
        }
        if (saleOrderDto.getOrderDate() == null) {
            errors.add("Please enter saleOrder date");
        }
        if (saleOrderDto.getClient() == null) {
            errors.add("Please select saleOrder client");
        }

        return errors;
    }
}
