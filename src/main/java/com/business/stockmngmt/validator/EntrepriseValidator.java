package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param EntrepriseDto entrepriseDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Please enter entreprise name");
            errors.add("Please enter entreprise email address");
            errors.add("Please enter entreprise phone number");
        }
        if (!StringUtils.hasLength(entrepriseDto.getName())) {
            errors.add("Please enter entreprise firstname");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmailEnt())) {
            errors.add("Please enter entreprise email address");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTelEnt())) {
            errors.add("Please enter entreprise phone number");
        }

        return errors;
    }
}
