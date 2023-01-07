package dev.senzalla.contacts.service.mail;

import dev.senzalla.contacts.model.mail.Mail;
import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.settings.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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


    public void orderRecoverAccount(User user) {
        Mail mail = new Mail();
        String html = mail.getMailSender();
        sendMail(user.getMailUser(), RECOVER_ACCOUNT, html);
    }

    private void sendMail(String mailUser, String recoverAccount, String html) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(mailApp);
            helper.setTo(mailUser);
            helper.setText(html, true);
            helper.setSubject(recoverAccount);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException();
        }
    }
}
