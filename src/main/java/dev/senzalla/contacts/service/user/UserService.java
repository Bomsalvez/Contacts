package dev.senzalla.contacts.service.user;

import dev.senzalla.contacts.model.permission.module.PromotionAuthorityUser;
import dev.senzalla.contacts.model.recoveraccount.module.ResettingPassword;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserCreating;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final CreateUserService createUserService;
    private final FindUserService findUserService;
    private final FindMultipleUserService findMultipleUserService;
    private final PromotionAuthorityUserService promotionAuthorityUserService;
    private final DeleteUserService deleteUserService;

    public UserCreated createUser(UserCreating userCreating) {
        return createUserService.createUser(userCreating);
    }

    public UserCreated findUserCreated(Long pkUser) {
        return findUserService.findUserCreated(pkUser);
    }

    public User findUser(Long pkUser) {
        return findUserService.findUser(pkUser);
    }

    public User findUserByMail(String mail) {
        return findUserService.findUserByMail(mail);
    }

    public Page<UserSummarize> findListUser(Pageable pageable, String nameUser, String mailUser) {
        return findMultipleUserService.findMultipleUser(pageable, nameUser, mailUser);
    }

    public UserCreated editUser(Long pkUser, UserCreating userCreating) {
        return createUserService.editUser(pkUser, userCreating);
    }

    public UserCreated promotionUser(Long pkUser, PromotionAuthorityUser promotionAuthorityUser) {
        return promotionAuthorityUserService.promotionAuthorityUser(pkUser, promotionAuthorityUser);
    }

    public void deleteUser(Long pkUser) {
        deleteUserService.deleteUser(pkUser);
    }

    public UserCreated changePassword(ResettingPassword resettingPassword) {
        return createUserService.changePassword(resettingPassword);
    }
}
