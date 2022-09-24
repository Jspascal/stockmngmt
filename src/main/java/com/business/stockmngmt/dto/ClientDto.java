package com.business.stockmngmt.dto;

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

    private List<SaleOrderDto> saleOrders;

    /*
     * function that allow us to map from Client to ClientDto
     *
     * @param Client client
     * */
    public static ClientDto fromEntity (Client client) {
        if (client == null) {
            //TODO raise an exception

            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .firstname(client.getFirstname())
                .lastname(client.getLastname())
                .photoClient(client.getPhotoClient())
                .emailClient(client.getEmailClient())
                .numTelClient(client.getNumTelClient())
                .build();
    }

    /*
     * function that allow us to map from ClientDto to Client
     *
     * @param ClientDto clientDto
     * */
    public static Client toEntity (ClientDto clientDto) {
        if (clientDto == null) {
            //TODO raise an exception

            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setFirstname(clientDto.getFirstname());
        client.setLastname(clientDto.getLastname());
        client.setPhotoClient(clientDto.getPhotoClient());
        client.setEmailClient(clientDto.getEmailClient());
        client.setNumTelClient(clientDto.getNumTelClient());

        return client;
    }
}
