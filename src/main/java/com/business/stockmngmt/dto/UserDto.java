package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Address;
import com.business.stockmngmt.model.Entreprise;
import com.business.stockmngmt.model.Role;
import com.business.stockmngmt.model.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Data
public class UserDto {

    private Integer id;

    private String firstname;

    private String lastname;

    @Embedded
    private Address address;

    private String photoUser;

    private String emailUser;

    private String password;

    private String numTelUser;

    private Entreprise entreprise;

    private Role role;

    /*
     * function that allow us to map from User to UserDto
     *
     * @param User user
     * */
    public static UserDto fromEntity (User user) {
        if (user == null) {
            //TODO raise an exception

            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .photoUser(user.getPhotoUser())
                .emailUser(user.getEmailUser())
                .password(user.getPassword())
                .numTelUser(user.getNumTelUser())
                .entreprise(user.getEntreprise())
                .role(user.getRole())
                .build();
    }

    /*
     * function that allow us to map from UserDto to User
     *
     * @param UserDto userDto
     * */
    public static User toEntity (UserDto userDto) {
        if (userDto == null) {
            //TODO raise an exception

            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPhotoUser(userDto.getPhotoUser());
        user.setEmailUser(userDto.getEmailUser());
        user.setPassword(userDto.getPassword());
        user.setNumTelUser(userDto.getNumTelUser());
        user.setEntreprise(userDto.getEntreprise());
        user.setRole(userDto.getRole());

        return user;
    }
}
