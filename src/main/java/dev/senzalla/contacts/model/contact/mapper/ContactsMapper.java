package dev.senzalla.contacts.model.contact.mapper;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.module.ContactsDto;
import dev.senzalla.contacts.settings.bean.ModelMapperBean;

public class ContactsMapper extends ModelMapperBean {
   public static Contacts toContacts(ContactsDto contactsDto) {
        return mapper.map(contactsDto, Contacts.class);
    }

    public static ContactsDto toContactsDto(Contacts contacts) {
        return mapper.map(contacts, ContactsDto.class);
    }
}