package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.ProviderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param ProviderDto providerDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(ProviderDto providerDto) {
        List<String> errors = new ArrayList<>();

        if (providerDto == null) {
            errors.add("Please enter provider firstname");
            errors.add("Please enter provider lastname");
            errors.add("Please enter provider email address");
            errors.add("Please enter phone number");
        }
        if (!StringUtils.hasLength(providerDto.getFirstname())) {
            errors.add("Please enter provider firstname");
        }
        if (!StringUtils.hasLength(providerDto.getLastname())) {
            errors.add("Please enter provider lastname");
        }
        if (!StringUtils.hasLength(providerDto.getEmailProvider())) {
            errors.add("Please enter provider email address");
        }
        if (!StringUtils.hasLength(providerDto.getNumTelProvider())) {
            errors.add("Please enter provider phone number");
        }

        return errors;
    }
}
