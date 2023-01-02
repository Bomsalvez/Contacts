package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import dev.senzalla.contacts.repository.UserRepository;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SearchUserService {
    private final UserRepository userRepository;

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

    public Page<UserSummarize> findListUser(Pageable pageable, String nameUser, String mailUser) {
        Page<User> users = userRepository.findByNameUserAndMailUser(pageable, nameUser, mailUser);
        return users.map(UserMapper::toUserSummarize);
    }
}
