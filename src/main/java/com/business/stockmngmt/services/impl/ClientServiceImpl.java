package com.business.stockmngmt.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.business.stockmngmt.dto.ClientDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.repository.ClientRepository;
import com.business.stockmngmt.services.ClientService;
import com.business.stockmngmt.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * The function takes a ClientDto object, validates it, and if it's valid, saves
     * it to the database
     *
     * @param clientDto The object that is being validated.
     * @return ClientDto
     */
    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(clientDto)));
    }

    /**
     * It takes an ID, finds the client with that ID, and returns the client as a
     * DTO
     *
     * @param id The ID of the client to be found.
     * @return A ClientDto object
     */
    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null;
        }

        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.CLIENT_NOT_FOUND));
    }

    /**
     * This function takes a list of Client entities, maps them to ClientDto
     * objects, and returns a
     * list of ClientDto objects.
     *
     * @return A list of ClientDto objects.
     */
    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * The function deletes a client from the database by its ID
     *
     * @param id The id of the client to be deleted.
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return;
        }

        clientRepository.deleteById(id);
    }

}
