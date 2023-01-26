package dev.senzalla.contacts.model.phonenumber.mapper;

import dev.senzalla.contacts.model.phonenumber.entity.PhoneNumber;
import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import org.modelmapper.ModelMapper;

public class PhoneNumberMapper {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    private PhoneNumberMapper() {
    }

    public static PhoneNumber toPhoneNumber(PhoneNumberDto phoneNumberDto) {
        return mapper.map(phoneNumberDto, PhoneNumber.class);
    }
}
