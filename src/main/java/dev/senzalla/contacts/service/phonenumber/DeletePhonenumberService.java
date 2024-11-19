package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.phonenumber.entity.PhoneNumber;
import dev.senzalla.contacts.repository.PhonenumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeletePhonenumberService {
    private final PhonenumberRepository phonenumberRepository;

    public void delete(Set<PhoneNumber> phoneNumbers) {
        phonenumberRepository.deleteAll(phoneNumbers);
    }

    public void deletePhoneToContact(Long pkContact, Long pkPhonenumber) {
        phonenumberRepository.deletePhoneToContact(pkContact, pkPhonenumber);
    }
}
