package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.service.token.SearchTokenService;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FindUserService {
    private final UserRepository userRepository;
    private final SearchTokenService tokenService;

    public UserCreated findUserCreated(Long pkUser) {
        User user = findUser(pkUser);
        return UserMapper.toUserCreated(user);
    }

    public User findUser(Long pkUser) {
        return userRepository.findById(pkUser).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public User findUserByMail(String mail) {
        return userRepository.findUserByMailUser(mail).orElseThrow(() -> new NotFoundException("User Not Found"));
    }

    public UserCreated findUserCreated(String token) {

        Long pkUser = tokenService.getIdUser(getToken(token));
        User user = findUser(pkUser);
        return UserMapper.toUserCreated(user);
    }

    private String getToken(String token) {
        if (token != null && token.startsWith("Bearer")) {
            return token.substring(7);
        }
        return null;
    }
}
