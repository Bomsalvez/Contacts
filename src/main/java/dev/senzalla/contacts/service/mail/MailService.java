package dev.senzalla.contacts.service.mail;

import dev.senzalla.contacts.model.recoveraccount.entity.RecoverAccount;
import dev.senzalla.contacts.settings.service.ThymeleafBean;
import dev.senzalla.contacts.settings.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {
    private static final String CREATE_ACCOUNT = "Bem Vinda!";
    private static final String RECOVER_ACCOUNT = "Ola Novamente!";
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String mailApp;


    public void defineHtmlToRecoverAccount(RecoverAccount account) {
        Map<String, Object> variablesHtml = new HashMap<>();
        variablesHtml.put("mailUser", account.getMailUser());
        variablesHtml.put("nameUser", account.getNameUser());
        variablesHtml.put("hashSecurity", account.getHashSecurity());
        String html = new ThymeleafBean().createContext("RecoverAccount.html", variablesHtml);
        sendMail(account, RECOVER_ACCOUNT, html);
    }

    private void sendMail(RecoverAccount account, String recoverAccount, String html) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(mailApp);
            helper.setTo(account.getMailUser());
            helper.setText(html, true);
            helper.setSubject(recoverAccount);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException();
        }
    }
}
