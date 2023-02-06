package dev.senzalla.contacts.controller.contact;

import dev.senzalla.contacts.Tokens;
import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.repository.ContactsRepository;
import dev.senzalla.contacts.repository.MailRepository;
import dev.senzalla.contacts.repository.PhonenumberRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteContactTest {
    private final String urlTemplate = "/contact/{pkContact}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsRepository contactsRepository;
    @MockBean
    private MailRepository mailRepository;
    @MockBean
    private PhonenumberRepository phonenumberRepository;

    @Test
    void shouldOkDeleteContactByPkContact() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.any())).thenReturn(Optional.of(new Contacts()));
        Mockito.doNothing().when(mailRepository).deleteAll(Mockito.anySet());
        Mockito.doNothing().when(phonenumberRepository).deleteAll(Mockito.anySet());
        ResultActions perform = mockMvc.perform(delete(urlTemplate, 0).header("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void shouldNotFoundDeleteContactByPkContactMissing() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.any())).thenReturn(Optional.empty());
        ResultActions perform = mockMvc.perform(delete(urlTemplate, 0).header("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void shouldBadRequestDeleteContactByPkContactWithPkContactNotBeingNumber() throws Exception {
        ResultActions perform = mockMvc.perform(delete(urlTemplate, "string").header("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isBadRequest());
    }
}
