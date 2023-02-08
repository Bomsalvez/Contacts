package dev.senzalla.contacts.controller.mail;

import dev.senzalla.contacts.constants.Errors;
import dev.senzalla.contacts.constants.Tokens;
import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.mail.entity.Mail;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.repository.MailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddMailToContactTest {
    private final String urlTemplate = "/mail/{pkContact}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailRepository mailRepository;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    void shouldOkWhenAddMailToContactTest() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.anyLong())).thenReturn(Optional.of(new Contacts()));
        Mockito.when(mailRepository.save(Mockito.any())).thenReturn(new Mail());

        ResultActions perform = mockMvc.perform(post(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"mail\":  \"mail@mail.com\"}]")
        );

        perform.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldFailureWhenAddMailToContactMissingMailTest() throws Exception {
        ResultActions perform = mockMvc.perform(post(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailureWhenAddMailToContactWithDuplicateMailTest() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.anyLong())).thenReturn(Optional.of(new Contacts()));
        Mockito.when(mailRepository.save(Mockito.any())).thenThrow(Errors.dataIntegrityViolationException);

        ResultActions perform = mockMvc.perform(post(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"mail\":  \"mail@mail.com\"}]")
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }
}
