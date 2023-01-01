package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SearchUserService {
    private final UserRepository userRepository;

    public UserCreated findUserCreated(Long pkUser) {
        User user = findUser(pkUser);
        return UserMapper.toUserDto(user);
    }

    public User findUser(Long pkUser) {
        return userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public User findUserByMail(String mail) {
        return userRepository.findUserByMailUser(mail).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

}
