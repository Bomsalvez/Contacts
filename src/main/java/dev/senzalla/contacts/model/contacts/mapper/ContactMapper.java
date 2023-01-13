package dev.senzalla.contacts.model.contacts.mapper;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import dev.senzalla.contacts.model.contacts.module.ContactSummarize;
import dev.senzalla.contacts.model.contacts.module.ContactSummarizeMinus;
import dev.senzalla.contacts.model.contacts.module.ContactsCreated;
import dev.senzalla.contacts.model.contacts.module.ContactsCreating;
import org.modelmapper.ModelMapper;

public class ContactMapper {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    private ContactMapper() {
    }

    public static Contacts toContacts(ContactsCreating contactsCreating) {
        return mapper.map(contactsCreating, Contacts.class);
    }

    public static ContactsCreated toContactsCreated(Contacts contacts) {
        return mapper.map(contacts, ContactsCreated.class);
    }

    public static ContactSummarize toContactSummarize(Contacts contacts) {
        return mapper.map(contacts, ContactSummarize.class);
    }

    public static ContactSummarizeMinus toContactSummarizeMinus(Contacts contacts) {
        return mapper.map(contacts, ContactSummarizeMinus.class);
    }
}
