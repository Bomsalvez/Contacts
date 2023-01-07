package dev.senzalla.contacts.model.mail;

import lombok.Getter;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Getter
public class Mail {
    private final String mailSender;

    public Mail() {
        try {
            File file = ResourceUtils.getFile("classpath:static/RecoverAccount.html");
            this.mailSender = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
