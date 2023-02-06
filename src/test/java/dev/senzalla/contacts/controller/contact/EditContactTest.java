package dev.senzalla.contacts.controller.contact;

import dev.senzalla.contacts.Tokens;
import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.repository.ContactsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EditContactTest {
    private final String urlTemplate = "/contact/{pkContact}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    void shouldOkWhenEditContact() throws Exception {
        Mockito.when(contactsRepository.save(Mockito.any())).thenReturn(new Contacts());

        ResultActions perform = mockMvc.perform(put(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nameContact\": \"Teste\"}")
        );

        perform.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldBadRequestWhenAddContactWithNameContactMissing() throws Exception {
        ResultActions perform = mockMvc.perform(put(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nameContact\": \"\"}")
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void shouldBadRequestWhenAddContactWithDateBirthContactWrong() throws Exception {
        ResultActions perform = mockMvc.perform(put(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nameContact\": \"Teste\", \"dateBirthContact\": \"202-01-20\"}")
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void shouldBadRequestWhenAddContactWithDateBirthContactFuture() throws Exception {
        ResultActions perform = mockMvc.perform(put(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nameContact\": \"Teste\", \"dateBirthContact\": \"2024-01-20\"}")
        );

        perform.andDo(print()).andExpect(status().isBadRequest());
    }
}
