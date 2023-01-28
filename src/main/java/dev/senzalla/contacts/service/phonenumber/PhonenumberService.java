package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import dev.senzalla.contacts.model.phonenumber.module.PhonenumberDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PhonenumberService {
    private final SavePhonenumberService savePhonenumberService;
    private final DeletePhonenumberService deletePhonenumberService;

    public void addPhonenumberToContact(Set<PhonenumberDto> phonenumbers, Contacts contacts) {
        savePhonenumberService.addPhonenumberToContact(phonenumbers, contacts);
    }

    public void deletePhonenumber(Set<Phonenumber> phonenumbers) {
        deletePhonenumberService.delete(phonenumbers);
    }
}
