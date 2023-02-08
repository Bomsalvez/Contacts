package dev.senzalla.contacts.controller.phonenumber;

import dev.senzalla.contacts.constants.Errors;
import dev.senzalla.contacts.constants.Tokens;
import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.phonenumber.entity.Phonenumber;
import dev.senzalla.contacts.repository.ContactsRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddPhonenumberToContactTest {
    private final String urlTemplate = "/phone/{pkContact}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhonenumberRepository phonenumberRepository;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    void shouldOkWhenAddPhonenumberToContactTest() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.anyLong())).thenReturn(Optional.of(new Contacts()));
        Mockito.when(phonenumberRepository.save(Mockito.any())).thenReturn(new Phonenumber());

        ResultActions perform = mockMvc.perform(post(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"phoneNumber\":  \"5436981165\"}]")
        );

        perform.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldFailureWhenAddPhonenumberToContactMissingPhonenumberTest() throws Exception {
        ResultActions perform = mockMvc.perform(post(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailureWhenAddPhonenumberToContactWithDuplicatePhonenumberTest() throws Exception {
        Mockito.when(contactsRepository.findByPkContact(Mockito.anyLong())).thenReturn(Optional.of(new Contacts()));
        Mockito.when(phonenumberRepository.save(Mockito.any())).thenThrow(Errors.dataIntegrityViolationException);

        ResultActions perform = mockMvc.perform(post(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"phoneNumber\":  \"5436981165\"}]")
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }
}
