package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.StockMvtDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StockMvtValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param StockMvtDto stockMvtDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(StockMvtDto stockMvtDto) {
        List<String> errors = new ArrayList<>();

        if (stockMvtDto == null) {
            errors.add("Please enter stock movement date");
            errors.add("Please enter stock movement quantity");
            errors.add("Please enter stock movement type");
            errors.add("Please select stock movement article");
        }
        if (stockMvtDto.getMvtDate() == null) {
            errors.add("Please enter stock movement date");
        }
        if (stockMvtDto.getQuantity() == null) {
            errors.add("Please enter stock movement quantity");
        }
        if (stockMvtDto.getMvtType() == null) {
            errors.add("Please enter stock movement type");
        }
        if (stockMvtDto.getArticle() == null) {
            errors.add("Please enter stock movement article");
        }

        return errors;
    }
}
