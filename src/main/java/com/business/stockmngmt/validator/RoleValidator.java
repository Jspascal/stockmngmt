package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param RoleDto roleDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(RoleDto roleDto) {
        List<String> errors = new ArrayList<>();

        if (roleDto == null) {
            errors.add("Please enter role firstname");
            errors.add("Please enter role lastname");
        }
        if (!StringUtils.hasLength(roleDto.getLabel())) {
            errors.add("Please enter role label");
        }
        if (!StringUtils.hasLength(roleDto.getDescription())) {
            errors.add("Please enter role description");
        }

        return errors;
    }
}
