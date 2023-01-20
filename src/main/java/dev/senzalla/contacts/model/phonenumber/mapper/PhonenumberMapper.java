package dev.senzalla.contacts.model.phonenumber.mapper;

import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberDto;
import dev.senzalla.contacts.settings.bean.ModelMapperBean;

public class PhonenumberMapper extends ModelMapperBean {
    public static Phonenumber toPhonenumber(PhonenumberDto phonenumberDto) {
        return mapper.map(phonenumberDto, Phonenumber.class);
    }

    public static PhonenumberDto toPhonenumberDto(Phonenumber phonenumber) {
        return mapper.map(phonenumber, PhonenumberDto.class);
    }
}