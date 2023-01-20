package dev.senzalla.contacts.model.mail.mapper;

import dev.senzalla.contacts.model.mail.entity.Mail;
import dev.senzalla.contacts.model.mail.module.MailDto;
import dev.senzalla.contacts.settings.bean.ModelMapperBean;

public class MailMapper extends ModelMapperBean {
    public static Mail toMail(MailDto mailDto) {
        return mapper.map(mailDto, Mail.class);
    }

    public static MailDto toMailDto(Mail mail) {
        return mapper.map(mail, MailDto.class);
    }
}