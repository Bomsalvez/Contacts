package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.permission.module.PermissionPromotion;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserDto;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final SearchUserService searchUserService;
    private final CreateUserService createUserService;

    public UserCreated createUser(UserDto userDto) {
        return createUserService.createUser(userDto);
    }

    public UserCreated findUserCreated(Long pkUser) {
        return searchUserService.findUserCreated(pkUser);
    }

    public User findUser(Long pkUser) {
        return searchUserService.findUser(pkUser);
    }

    public User findUserByMail(String mail) {
        return searchUserService.findUserByMail(mail);
    }

    public Page<UserSummarize> findListUser(Pageable pageable, String nameUser, String mailUser) {
        return searchUserService.findListUser(pageable, nameUser, mailUser);
    }

    public UserCreated editUser(Long pkUser, UserDto userDto, String token) {
        return createUserService.editUser(pkUser, userDto, token);
    }

    public UserCreated promotionUser(Long pkUser, PermissionPromotion permissionPromotion) {
        return createUserService.promotionUser(pkUser, permissionPromotion);
    }
}
