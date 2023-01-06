package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.mapper.UserMapper;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import dev.senzalla.contacts.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FindMultipleUserService {
    private final UserRepository userRepository;

    public Page<UserSummarize> findMultipleUser(Pageable pageable, String nameUser, String mailUser) {
        Page<User> users = userRepository.findByNameUserAndMailUser(pageable, nameUser, mailUser);
        return users.map(UserMapper::toUserSummarize);
    }
}
