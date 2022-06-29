package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.RoleDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Role;
import com.business.stockmngmt.repository.RoleRepository;
import com.business.stockmngmt.services.RoleService;
import com.business.stockmngmt.validator.RoleValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param roleDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return RoleDto
     */
    @Override
    public RoleDto save(RoleDto roleDto) {
        List<String> errors = RoleValidator.validate(roleDto);
        if(!errors.isEmpty()) {
            log.error("Role is not valid {}", roleDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return RoleDto.fromEntity(
                roleRepository.save(
                        RoleDto.toEntity(roleDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return RoleDto
     */
    @Override
    public RoleDto findById(Integer id) {
        if (id == null) {
            log.error("Role ID is null");
            return null;
        }

        Optional<Role> role = roleRepository.findById(id);

        return Optional.of(RoleDto.fromEntity(role.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND
                )
        );
    }

    @Override
    public List<RoleDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        roleRepository.deleteById(id);
    }

}
