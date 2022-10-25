package com.business.stockmngmt.dto;

import com.business.stockmngmt.exception.EntityNotFoundException;
import com.business.stockmngmt.exception.ErrorCodes;
import com.business.stockmngmt.model.Address;
import com.business.stockmngmt.model.Client;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Embedded;
import java.util.List;

@Builder
@Data
public class ClientDto {

    private Integer id;

    private String firstname;

    private String lastname;

    @Embedded
    private Address address;

    private String photoClient;

    private String emailClient;

    private String numTelClient;

    private Integer idEntreprise;

    private List<SaleOrderDto> saleOrders;

    /*
     * function that allow us to map from Client to ClientDto
     *
     * @param Client client
     * */
    public static ClientDto fromEntity (Client client) {
        if (client == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.CLIENT_NOT_FOUND);

        }

        return ClientDto.builder()
                .id(client.getId())
                .firstname(client.getFirstname())
                .lastname(client.getLastname())
                .photoClient(client.getPhotoClient())
                .emailClient(client.getEmailClient())
                .numTelClient(client.getNumTelClient())
                .idEntreprise(client.getIdEntreprise())
                .build();
    }

    /*
     * function that allow us to map from ClientDto to Client
     *
     * @param ClientDto clientDto
     * */
    public static Client toEntity (ClientDto clientDto) {
        if (clientDto == null) {
            throw new EntityNotFoundException("Couldn't find object", ErrorCodes.CLIENT_NOT_FOUND);

        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setPhotoClient(clientDto.getPhotoClient());
        client.setEmailClient(clientDto.getEmailClient());
        client.setNumTelClient(clientDto.getNumTelClient());
        client.setIdEntreprise(clientDto.getIdEntreprise());

        return client;
    }
}
