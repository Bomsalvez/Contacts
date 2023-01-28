package dev.senzalla.contacts.model.contact.mapper;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.contact.module.ContactsDto;
import dev.senzalla.contacts.model.contact.module.ContactsMinimal;
import dev.senzalla.contacts.model.contact.module.ContactsSummarize;
import dev.senzalla.contacts.settings.bean.ModelMapperBean;

public class ContactsMapper extends ModelMapperBean {
    public static Contacts toContacts(ContactsDto contactsDto) {
        return mapper.map(contactsDto, Contacts.class);
    }

    public static ContactsCreated toContactsCreated(Contacts contacts) {
        return mapper.map(contacts, ContactsCreated.class);
    }

    public static ContactsMinimal toContactsMinimal(Contacts contacts) {
        return mapper.map(contacts, ContactsMinimal.class);
    }

    public static ContactsSummarize toContactsSummarize(Contacts contacts) {
        return mapper.map(contacts, ContactsSummarize.class);
    }
}