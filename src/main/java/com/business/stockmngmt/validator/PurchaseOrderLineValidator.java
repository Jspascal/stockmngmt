package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.PurchaseOrderLineDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderLineValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param PurchaseOrderLineDto purchaseOrderLineDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(PurchaseOrderLineDto purchaseOrderLineDto) {
        List<String> errors = new ArrayList<>();

        if (purchaseOrderLineDto == null) {
            errors.add("Article cannot be null");
            errors.add("Purchase Order cannot be null");
        }
        if (purchaseOrderLineDto.getArticle() == null) {
            errors.add("Article cannot be null");
        }
        if (purchaseOrderLineDto.getPurchaseOrder() == null) {
            errors.add("Purchase order cannot be null");
        }

        return errors;
    }
}
