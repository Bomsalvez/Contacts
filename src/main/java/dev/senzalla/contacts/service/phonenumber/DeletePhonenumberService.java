package dev.senzalla.contacts.service.phonenumber;

import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import dev.senzalla.contacts.repository.PhonenumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeletePhonenumberService {
    private final PhonenumberRepository phonenumberRepository;

    public void delete(Set<Phonenumber> phonenumbers) {
        phonenumberRepository.deleteAll(phonenumbers);
    }
}
