package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.ProviderDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Provider;
import com.business.stockmngmt.repository.ProviderRepository;
import com.business.stockmngmt.services.ProviderService;
import com.business.stockmngmt.validator.ProviderValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    /**
     * It takes a ProviderDto object, validates it, and if it's valid, saves it to the database
     *
     * @param providerDto The object that is being validated.
     * @return ProviderDto
     */
    @Override
    public ProviderDto save(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if (!errors.isEmpty()) {
            log.error("Provider is not valid {}", providerDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PROVIDER_NOT_VALID, errors);
        }
        return ProviderDto.fromEntity(
                providerRepository.save(
                        ProviderDto.toEntity(providerDto)));
    }

    /**
     * If the id is null, log an error and return null, otherwise, return the providerDto from the
     * entity or throw an exception if the entity is not found.
     *
     * @param id The ID of the provider to be found
     * @return A ProviderDto object
     */
    @Override
    public ProviderDto findById(Integer id) {
        if (id == null) {
            log.error("Provider ID is null");
            return null;
        }

        return providerRepository.findById(id)
                .map(ProviderDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PROVIDER_NOT_FOUND));
    }

    /**
     * This function returns a list of ProviderDto objects, which are created by mapping the
     * ProviderRepository's findAll() function to a stream of ProviderDto objects, which are then
     * collected into a list.
     *
     * @return A list of ProviderDto objects.
     */
    @Override
    public List<ProviderDto> findAll() {
        return providerRepository.findAll().stream()
                .map(ProviderDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * The function deletes a provider from the database
     *
     * @param id The ID of the provider to delete.
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Provider ID is null");
            return;
        }

        providerRepository.deleteById(id);
    }

}
