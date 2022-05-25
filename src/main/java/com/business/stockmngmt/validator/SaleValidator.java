package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.SaleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param SaleDto saleDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(SaleDto saleDto) {
        List<String> errors = new ArrayList<>();

        if (saleDto == null) {
            errors.add("Please enter sale code");
            errors.add("Please enter sale date");
        }
        if (!StringUtils.hasLength(saleDto.getSaleCode())) {
            errors.add("Please enter sale code");
        }
        if (saleDto.getSaleDate() == null) {
            errors.add("Please enter sale date");
        }

        return errors;
    }
}
