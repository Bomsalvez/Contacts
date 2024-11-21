package dev.senzalla.contacts.model.phonenumber.mapper;

import dev.senzalla.contacts.model.phonenumber.entity.PhoneNumber;
import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import dev.senzalla.contacts.settings.bean.ModelMapperBean;

public class PhonenumberMapper extends ModelMapperBean {
    public static PhoneNumber toPhonenumber(PhoneNumberDto phonenumberDto) {
        return mapper.map(phonenumberDto, PhoneNumber.class);
    }
}