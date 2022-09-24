package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param ClientDto clientDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (clientDto == null) {
            errors.add("Please enter client firstname");
            errors.add("Please enter client lastname");
            errors.add("Please enter client email address");
            errors.add("Please enter phone number");
        }
        if (!StringUtils.hasLength(clientDto.getFirstname())) {
            errors.add("Please enter client firstname");
        }
        if (!StringUtils.hasLength(clientDto.getLastname())) {
            errors.add("Please enter client lastname");
        }
        if (!StringUtils.hasLength(clientDto.getEmailClient())) {
            errors.add("Please enter client email address");
        }
        if (!StringUtils.hasLength(clientDto.getNumTelClient())) {
            errors.add("Please enter client phone number");
        }

        return errors;
    }
}
