package dev.senzalla.contacts.model.address.mapper;

import dev.senzalla.contacts.model.address.entity.Address;
import dev.senzalla.contacts.model.address.module.AddressDto;
import dev.senzalla.contacts.settings.bean.ModelMapperBean;

public class AddressMapper extends ModelMapperBean {
    public static Address toAddress(AddressDto addressDto) {
        return mapper.map(addressDto, Address.class);
    }

    public static AddressDto toAddressDto(Address address) {
        return mapper.map(address, AddressDto.class);
    }
}