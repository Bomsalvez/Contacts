package dev.senzalla.contacts.controller.contact;

import dev.senzalla.contacts.constants.Tokens;
import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.repository.ContactsRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FindContactTest {
    private final String urlTemplate = "/contact/{pkContact}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    void shouldOkFindContactByPkContact() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.any())).thenReturn(Optional.of(new Contacts()));
        ResultActions perform = mockMvc.perform(get(urlTemplate, 0).header("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldNotFoundFindContactByPkContactMissing() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.any())).thenReturn(Optional.empty());
        ResultActions perform = mockMvc.perform(get(urlTemplate,0).header("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void shouldBadRequestFindContactByPkContactWithPkContactNotBeingNumber() throws Exception {
        ResultActions perform = mockMvc.perform(get(urlTemplate, "string").header("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isBadRequest());
    }
}
