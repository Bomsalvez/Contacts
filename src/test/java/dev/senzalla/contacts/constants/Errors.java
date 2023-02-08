package dev.senzalla.contacts.constants;

import org.springframework.dao.DataIntegrityViolationException;

public class Errors {
    public static DataIntegrityViolationException dataIntegrityViolationException = new DataIntegrityViolationException("erro pra teste", new Throwable());
}
