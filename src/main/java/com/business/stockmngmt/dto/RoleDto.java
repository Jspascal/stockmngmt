package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Role;
import com.business.stockmngmt.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDto {

    private Integer id;

    private String label;

    private String description;

    public static RoleDto fromEntity (Role role) {
        if (role == null) {
            //TODO raise an exception

            return null;
        }

        return RoleDto.builder()
                .id(role.getId())
                .label(role.getLabel())
                .description(role.getDescription())
                .build();
    }

    /*
     * function that allow us to map from RoleDto to Role
     *
     * @param RoleDto roleDto
     * */
    public static Role toEntity (RoleDto roleDto) {
        if (roleDto == null) {
            //TODO raise an exception

            return null;
        }

        Role role = new Role();
        role.setId(roleDto.getId());
        role.setLabel(roleDto.getLabel());
        role.setDescription(roleDto.getDescription());

        return role;
    }
}
