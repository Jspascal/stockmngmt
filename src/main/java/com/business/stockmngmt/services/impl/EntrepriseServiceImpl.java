package com.business.stockmngmt.services.impl;

import com.business.stockmngmt.dto.EntrepriseDto;
import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.exception.InvalidEntityException;
import com.business.stockmngmt.model.Entreprise;
import com.business.stockmngmt.repository.EntrepriseRepository;
import com.business.stockmngmt.services.EntrepriseService;
import com.business.stockmngmt.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    /**
     * The function validates the entrepriseDto object, if it's not valid, it throws an exception,
     * otherwise it saves the entrepriseDto object to the database
     *
     * @param entrepriseDto The object that is being validated.
     */
    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException("Article is not valid", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(entrepriseDto)));
    }

    /**
     * If the id is null, log an error and return null. Otherwise, return the entity mapped to a DTO,
     * or throw an exception if the entity is not found
     *
     * @param id The ID of the entity to be retrieved.
     * @return A DTO object
     */
    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }

        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No article with the ID " + id + "inside the database",
                        ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    /**
     * This function returns a list of EntrepriseDto objects, which are created by mapping the
     * Entreprise objects returned by the findAll() function of the EntrepriseRepository object, using
     * the fromEntity() function of the EntrepriseDto class.
     *
     * @return A list of EntrepriseDto
     */
    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * The function takes an integer as a parameter and deletes the entreprise with the given id
     *
     * @param id the id of the entreprise to delete
     */
    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }

        entrepriseRepository.deleteById(id);
    }

}
