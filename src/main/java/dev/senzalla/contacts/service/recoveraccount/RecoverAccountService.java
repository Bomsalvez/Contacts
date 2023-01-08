package dev.senzalla.contacts.service.recoveraccount;

import dev.senzalla.contacts.model.recoveraccount.entity.RecoverAccount;
import dev.senzalla.contacts.model.recoveraccount.module.ResettingPassword;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.repository.RecoverAccountRepository;
import dev.senzalla.contacts.service.mail.MailService;
import dev.senzalla.contacts.service.user.UserService;
import dev.senzalla.contacts.settings.exception.DateException;
import dev.senzalla.contacts.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecoverAccountService {
    private final UserService userService;
    private final MailService mailService;
    private final RecoverAccountRepository recoverAccountRepository;

    public void orderRecoverAccount(String mailUser) {
        RecoverAccount recoverAccount = defineUserToRecoverAccount(mailUser);
        mailService.defineHtmlToRecoverAccount(recoverAccount);
    }

    public UserCreated changePassword(String hash, ResettingPassword resettingPassword) {
        RecoverAccount recoverAccount = recoverAccountRepository.findByHashSecurityAndMailUser(hash, resettingPassword.mail()).orElseThrow(() -> new NotFoundException("Token esta incorreto"));
        boolean dateExpiration = recoverAccount.getExpirationDate().isAfter(LocalDateTime.now());
        if (dateExpiration) {
            return userService.changePassword(resettingPassword);
        }
        throw new DateException();
    }

    private RecoverAccount defineUserToRecoverAccount(String mailUser) {
        User user = userService.findUserByMail(mailUser);
        RecoverAccount account = new RecoverAccount();
        account.setNameUser(user.getNameUser());
        account.setMailUser(mailUser);
        account.setHashSecurity(createHash());
        account.setExpirationDate(LocalDateTime.now().plusHours(24));
        recoverAccountRepository.save(account);
        return account;
    }

    private String createHash() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
