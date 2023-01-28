package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import dev.senzalla.contacts.model.contacts.module.ContactsCreating;
import dev.senzalla.contacts.model.phonenumber.entity.PhoneNumber;
import dev.senzalla.contacts.model.phonenumber.mapper.PhoneNumberMapper;
import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import dev.senzalla.contacts.repository.PhoneNumberRepository;
import dev.senzalla.contacts.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SavePhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public void addPhoneNumberToContact(Contacts contacts, ContactsCreating contactsCreating) {
        for (PhoneNumberDto phonenumber : contactsCreating.getPhonenumbers()) {
            PhoneNumber phoneNumber = PhoneNumberMapper.toPhoneNumber(phonenumber);
            phoneNumber.setContacts(contacts);
            addPhoneNumberToContact(phoneNumber);
        }
    }

    private void addPhoneNumberToContact(PhoneNumber phoneNumber) {
        try {
            phoneNumberRepository.save(phoneNumber);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }
}
