package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final SearchUserService searchUserService;
    private final CreateUserService createUserService;


    public UserCreated findUserCreated(Long pkUser) {
        return searchUserService.findUserCreated(pkUser);
    }

    public User findUser(Long pkUser) {
        return searchUserService.findUser(pkUser);
    }

    public User findUserByMail(String mail) {
        return searchUserService.findUserByMail(mail);
    }

    public UserCreated createUser(UserDto userDto) {
        return createUserService.createUser(userDto);
    }

}
