package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Address;
import com.business.stockmngmt.model.Provider;
import com.business.stockmngmt.model.PurchaseOrder;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Embedded;
import java.util.List;

@Builder
@Data
public class ProviderDto {

    private Integer id;

    private String firstname;

    private String lastname;

    @Embedded
    private Address address;

    private String photoProvider;

    private String emailProvider;

    private String numTelProvider;

    private List<PurchaseOrderDto> purchaseOrders;

    /*
     * function that allow us to map from Provider to ProviderDto
     *
     * @param Provider provider
     * */
    public static ProviderDto fromEntity (Provider provider) {
        if (provider == null) {
            //TODO raise an exception

            return null;
        }

        return ProviderDto.builder()
                .id(provider.getId())
                .firstname(provider.getFirstname())
                .lastname(provider.getLastname())
                .photoProvider(provider.getPhotoProvider())
                .emailProvider(provider.getEmailProvider())
                .numTelProvider(provider.getNumProvider())
                .build();
    }

    /*
     * function that allow us to map from ProviderDto to Provider
     *
     * @param ProviderDto providerDto
     * */
    public static Provider toEntity (ProviderDto providerDto) {
        if (providerDto == null) {
            //TODO raise an exception

            return null;
        }

        Provider provider = new Provider();
        provider.setId(providerDto.getId());
        provider.setFirstname(providerDto.getFirstname());
        provider.setLastname(providerDto.getLastname());
        provider.setPhotoProvider(providerDto.getPhotoProvider());
        provider.setEmailProvider(providerDto.getEmailProvider());
        provider.setNumProvider(providerDto.getNumTelProvider());

        return provider;
    }
}
