package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.PurchaseOrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param PurchaseOrderDto purchaseOrderDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(PurchaseOrderDto purchaseOrderDto) {
        List<String> errors = new ArrayList<>();

        if (purchaseOrderDto == null) {
            errors.add("Please enter purchaseOrder Code");
            errors.add("Please enter purchaseOrder Date");
            errors.add("Please select purchaseOrder provider");
        }
        if (!StringUtils.hasLength(purchaseOrderDto.getCodeOrder())) {
            errors.add("Please enter purchaseOrder code");
        }
        if (purchaseOrderDto.getOrderDate() == null) {
            errors.add("Please enter purchaseOrder date");
        }
        if (purchaseOrderDto.getProvider() == null) {
            errors.add("Please select purchaseOrder provider");
        }

        return errors;
    }
}
