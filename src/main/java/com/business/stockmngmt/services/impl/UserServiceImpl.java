package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.UserDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.User;
import com.business.stockmngmt.repository.UserRepository;
import com.business.stockmngmt.services.UserService;
import com.business.stockmngmt.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param userDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return UserDto
     */
    @Override
    public UserDto save(UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if(!errors.isEmpty()) {
            log.error("User is not valid {}", userDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PURCHASE_ORDER_NOT_VALID, errors);
        }
        return UserDto.fromEntity(
                userRepository.save(
                        UserDto.toEntity(userDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return UserDto
     */
    @Override
    public UserDto findById(Integer id) {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }

        Optional<User> user = userRepository.findById(id);

        return Optional.of(UserDto.fromEntity(user.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PURCHASE_ORDER_NOT_FOUND
                )
        );
    }

    @Override
    public List<UserDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(id == null) {
            log.error("Purchase order line ID is null");
            return;
        }

        userRepository.deleteById(id);
    }

}
