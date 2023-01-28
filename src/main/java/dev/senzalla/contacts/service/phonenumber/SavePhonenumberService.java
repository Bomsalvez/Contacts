package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import dev.senzalla.contacts.model.phonenumber.mapper.PhonenumberMapper;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberDto;
import dev.senzalla.contacts.repository.PhonenumberRepository;
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

    public void addPhonenumberToContact(Set<PhonenumberDto> phonenumbersDto, Contacts contacts) {
        if (phonenumbersDto != null) {
            phonenumbersDto.stream().map(PhonenumberMapper::toPhonenumber).forEach(phonenumber -> addPhonenumber(phonenumber, contacts));
        }
    }

    private void addPhonenumber(Phonenumber phonenumber, Contacts contacts) {
        try {
            phonenumber.setContacts(contacts);
            phonenumberRepository.save(phonenumber);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(Objects.requireNonNull(ex.getRootCause()).getMessage());
        }
    }
}
