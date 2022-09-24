package com.business.stockmngmt.dto;

import com.business.stockmngmt.model.Address;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {

    private String address1;

    private String address2;

    private String town;

    private String postalCode;

    private String country;

    /*
    * function that allow us to map from Address to AddressDto
    *
    * @param Address address
    * */
    public static AddressDto fromEntity (Address address) {
        if (address == null) {
            //TODO raise an exception
            return null;
        }

        return AddressDto.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .town(address.getTown())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .build();
    }

    /*
     * function that allow us to map from AddressDto to Address
     *
     * @param Address addressDto
     * */
    public static Address toEntity (AddressDto addressDto) {
        if (addressDto == null) {
            //TODO raise an exception
            return null;
        }

        Address address = new Address();
        address.setAddress1(address.getAddress1());
        address.setAddress2(address.getAddress2());
        address.setTown(address.getTown());
        address.setCountry(address.getCountry());

        return address;
    }
}
