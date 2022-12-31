package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.User;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class SearchUserService {
    private final UserRepository userRepository;

    public User findUser(Long pkUser) {
        return userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public User findUserByMail(String mail) {
        return userRepository.findUserByMailUser(mail).orElseThrow(() -> new NotFoundException("User Not Found"));
    }
}
