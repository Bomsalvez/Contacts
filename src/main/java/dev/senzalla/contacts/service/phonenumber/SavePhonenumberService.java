package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.module.ContactsCreated;
import dev.senzalla.contacts.model.phonenumber.entity.PhoneNumber;
import dev.senzalla.contacts.model.phonenumber.mapper.PhonenumberMapper;
import dev.senzalla.contacts.model.phonenumber.module.PhoneNumberDto;
import dev.senzalla.contacts.repository.PhonenumberRepository;
import dev.senzalla.contacts.service.contact.FindContactService;
import dev.senzalla.contacts.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SavePhonenumberService {
    private final PhonenumberRepository phonenumberRepository;
    private final FindContactService contactService;

    public void addPhonenumberToContact(Set<PhoneNumberDto> phonenumbersDto, Contacts contacts) {
        if (phonenumbersDto != null) {
            phonenumbersDto.stream().map(PhonenumberMapper::toPhonenumber).forEach(phonenumber -> addPhonenumber(phonenumber, contacts));
        }
    }

    public ContactsCreated addPhonenumberToContact(Long pkContact, Set<PhoneNumberDto> phoneNumberDtos) {
        Contacts contacts = contactService.findContact(pkContact);
        addPhonenumberToContact(phoneNumberDtos, contacts);
        return contactService.findContactDto(pkContact);
    }

    private void addPhonenumber(PhoneNumber phonenumber, Contacts contacts) {
        try {
            phonenumber.setContacts(contacts);
            phonenumberRepository.save(phonenumber);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }
}
