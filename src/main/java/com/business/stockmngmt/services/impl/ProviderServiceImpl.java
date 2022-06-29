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

import java.util.List;
import java.util.Optional;

@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepository providerRepository;
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    /**
     * @param providerDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return ProviderDto
     */
    @Override
    public ProviderDto save(ProviderDto providerDto) {
        List<String> errors = ProviderValidator.validate(providerDto);
        if(!errors.isEmpty()) {
            log.error("Provider is not valid {}", providerDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.PROVIDER_NOT_VALID, errors);
        }
        return ProviderDto.fromEntity(
                providerRepository.save(
                        ProviderDto.toEntity(providerDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return ProviderDto
     */
    @Override
    public ProviderDto findById(Integer id) {
        if (id == null) {
            log.error("Provider ID is null");
            return null;
        }

        Optional<Provider> provider = providerRepository.findById(id);

        return Optional.of(ProviderDto.fromEntity(provider.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.PROVIDER_NOT_FOUND
                )
        );
    }

    @Override
    public List<ProviderDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

}
