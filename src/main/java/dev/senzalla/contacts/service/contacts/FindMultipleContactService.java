package dev.senzalla.contacts.service.contacts;

import dev.senzalla.contacts.model.contacts.entity.Contacts;
import dev.senzalla.contacts.model.contacts.mapper.ContactMapper;
import dev.senzalla.contacts.model.contacts.module.ContactList;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FindMultipleContactService {
    private final ContactsRepository userRepository;
    private final TokenService tokenService;

    public Page<ContactList> findMultipleContact(Pageable pageable, String nameContact, String token) {
        Page<Contacts> users = userRepository.findByNameContact(pageable, nameContact);
        token = tokenService.extractToken(token);
        boolean isTokenPresent = tokenService.checkValidToken(token);
        if (isTokenPresent) {
            return users.map(ContactMapper::toContactSummarize);
        } else {
            return users.map(ContactMapper::toContactSummarizeMinus);
        }
    }
}
