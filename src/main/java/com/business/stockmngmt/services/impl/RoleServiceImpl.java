package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.RoleDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.repository.RoleRepository;
import com.business.stockmngmt.services.RoleService;
import com.business.stockmngmt.validator.RoleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * It takes a RoleDto, validates it, and if it's valid, it saves it to the database
     *
     * @param roleDto The object that is being validated.
     * @return A RoleDto object
     */
    @Override
    public RoleDto save(RoleDto roleDto) {
        List<String> errors = RoleValidator.validate(roleDto);
        if (!errors.isEmpty()) {
            log.error("Role is not valid {}", roleDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return RoleDto.fromEntity(
                roleRepository.save(
                        RoleDto.toEntity(roleDto)));
    }

    /**
     * If the id is null, log an error and return null, otherwise, return the roleDto from the entity
     * or throw an exception if the entity is not found.
     *
     * @param id The ID of the role to be retrieved.
     * @return A RoleDto object
     */
    @Override
    public RoleDto findById(Integer id) {
        if (id == null) {
            log.error("Role ID is null");
            return null;
        }

        return roleRepository.findById(id)
                .map(RoleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND));
    }

    /**
     * It takes a list of Role entities, maps them to RoleDto objects, and returns a list of RoleDto
     * objects
     *
     * @return A list of RoleDto objects.
     */
    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * The function deletes a purchase order line by ID
     *
     * @param id The ID of the purchase order line to delete.
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        roleRepository.deleteById(id);
    }

}
