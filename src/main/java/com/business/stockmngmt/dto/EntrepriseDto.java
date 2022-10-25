package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Address;
import com.business.stockmngmt.model.Entreprise;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Embedded;

import java.util.List;

@Builder
@Data
public class EntrepriseDto {

    private Integer id;

    private String name;

    private String description;

    @Embedded
    private Address address;

    private String logoEnt;

    private String emailEnt;

    private String numTelEnt;

    private String website;

    private List<UserDto> users;

    /*
     * function that allow us to map from Entreprise to EntrepriseDto
     *
     * @param Entreprise entreprise
     * */
    public static EntrepriseDto fromEntity (Entreprise entreprise) {
        if (entreprise == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.ENTREPRISE_NOT_FOUND);

        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .name(entreprise.getName())
                .description(entreprise.getDescription())
                .logoEnt(entreprise.getLogoEnt())
                .emailEnt(entreprise.getEmailEnt())
                .numTelEnt(entreprise.getNumTelEnt())
                .website(entreprise.getWebsite())
                .build();
    }

    /*
     * function that allow us to map from EntrepriseDto to Entreprise
     *
     * @param EntrepriseDto entrepriseDto
     * */
    public static Entreprise toEntity (EntrepriseDto entrepriseDto) {
        if (entrepriseDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.ENTREPRISE_NOT_FOUND);

        }

        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setName(entrepriseDto.getName());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setLogoEnt(entrepriseDto.getLogoEnt());
        entreprise.setEmailEnt(entrepriseDto.getEmailEnt());
        entreprise.setNumTelEnt(entrepriseDto.getNumTelEnt());
        entreprise.setWebsite(entrepriseDto.getWebsite());

        return entreprise;
    }
}
