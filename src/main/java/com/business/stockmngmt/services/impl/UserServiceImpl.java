package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.UserDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.repository.UserRepository;
import com.business.stockmngmt.services.UserService;
import com.business.stockmngmt.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * It takes a UserDto, validates it, and if it's valid, saves it to the database
     *
     * @param userDto The object that is being validated.
     * @return UserDto.fromEntity(userRepository.save(UserDto.toEntity(userDto)));
     */
    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", userDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return UserDto.fromEntity(
                userRepository.save(
                        UserDto.toEntity(userDto)));
    }

    /**
     * If the id is null, log an error and return null. Otherwise, return the userDto from the entity
     * or throw an exception if the entity is not found
     *
     * @param id The ID of the user to be retrieved.
     * @return UserDto
     */
    @Override
    public UserDto findById(Integer id) {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }

        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND));
    }

    /**
     * It takes a list of users from the database, converts them to DTOs, and returns them
     *
     * @return A list of UserDto objects.
     */
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * The function deletes a user from the database by ID
     *
     * @param id The ID of the user to be deleted.
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("User ID is null");
            return;
        }

        userRepository.deleteById(id);
    }
}
