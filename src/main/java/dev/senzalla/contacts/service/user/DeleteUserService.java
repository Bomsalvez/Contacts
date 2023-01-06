package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeleteUserService {
    private final UserRepository userRepository;

    public void deleteUser(Long pkUser) {
        User user = userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
        userRepository.delete(user);
    }
}
