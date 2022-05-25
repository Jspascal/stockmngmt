package com.business.stockmngmt.validator;

import com.business.stockmngmt.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    /*
     * I use this function to validate the datas we get from the form
     *
     * @param UserDto userDto
     *
     * @return java.Util.List<String>
     * */
    public static List<String> validate(UserDto userDto) {
        List<String> errors = new ArrayList<>();

        if(userDto == null) {
            errors.add("Please enter the first name of this user");
            errors.add("Please enter the last name of this user");
            errors.add("Please enter the email address of this user");
            errors.add("Please enter the password of this user");
            errors.add("Please fill the address fields for this user");
        }
        if (!StringUtils.hasLength(userDto.getFirstname())) {
            errors.add("Please enter the first name of this user");
        }
        if (!StringUtils.hasLength(userDto.getLastname())) {
            errors.add("Please enter the last name of this user");
        }
        if (!StringUtils.hasLength(userDto.getEmailUser())) {
            errors.add("Please enter the email address of this user");
        }
        if (!StringUtils.hasLength(userDto.getPassword())) {
            errors.add("Please enter the password of this user");
        }
        if (!StringUtils.hasLength(userDto.getNumTelUser())) {
            errors.add("Please enter the phone number of this user");
        }
        if (userDto.getAddress() == null) {
            errors.add("Please fill the address fields for this user");
        } else {
            if (!StringUtils.hasLength(userDto.getAddress().getAddress1())) {
                errors.add("Please enter the primary address of this user");
            }
            if (!StringUtils.hasLength(userDto.getAddress().getPostalCode())) {
                errors.add("Please enter the postal code of this user");
            }if (!StringUtils.hasLength(userDto.getAddress().getTown())) {
                errors.add("Please enter the town of this user");
            }if (!StringUtils.hasLength(userDto.getAddress().getCountry())) {
                errors.add("Please enter the country of this user");
            }
            if (userDto.getRole() == null) {
                errors.add("Please select the role for this user");
            }
        }

        return errors;
    }
}
