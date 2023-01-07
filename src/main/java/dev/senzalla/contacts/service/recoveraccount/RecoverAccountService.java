package dev.senzalla.contacts.service.recoveraccount;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.RecoverAccount;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.service.mail.MailService;
import dev.senzalla.contacts.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecoverAccountService {
    private final UserService userService;
    private final MailService mailService;

    public void orderRecoverAccount(String mailUser) {
        User user = userService.findUserByMail(mailUser);
        mailService.orderRecoverAccount(user);
    }

    public UserCreated changePassword(RecoverAccount recoverAccount) {
        return userService.changePassword(recoverAccount);
    }
}
