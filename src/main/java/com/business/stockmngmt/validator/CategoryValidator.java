package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    /*
    * I use this function to validate the datas we get from the form
    *
    * @param CategoryDto categoryDto
    *
    * @return java.Util.List<String>
    * */
    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCodeCategory())) {
            errors.add("Please enter category code");
        }

        return errors;
    }
}
