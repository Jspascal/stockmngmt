package com.business.stockmngmt.services.impl;

import java.util.List;
import java.util.Optional;

import com.business.stockmngmt.dto.ClientDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Client;
import com.business.stockmngmt.repository.ClientRepository;
import com.business.stockmngmt.services.ClientService;
import com.business.stockmngmt.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * @param clientDto - DTO representation
     * @throws InvalidEntityException - throw an exception the validation fails
     * @return ClientDto
     */
    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if(!errors.isEmpty()) {
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(clientDto)
                )
        );
    }

    /**
     * @param id -
     * @throws EntityNotFoundException -
     * @return ClientDto
     */
    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null;
        }

        Optional<Client> client = clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.CLIENT_NOT_FOUND
                )
        );
    }

    @Override
    public List<ClientDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

}
