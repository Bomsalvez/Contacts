package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final SearchUserService searchUserService;


    public User findUser(Long pkUser) {
        return searchUserService.findUser(pkUser);
    }


    public User findUserByMail(String mail) {
        return searchUserService.findUserByMail(mail);
    }
}
