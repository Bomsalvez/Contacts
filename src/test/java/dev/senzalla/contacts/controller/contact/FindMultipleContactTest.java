package dev.senzalla.contacts.controller.contact;

import dev.senzalla.contacts.Tokens;
import dev.senzalla.contacts.controller.ContactController;
import dev.senzalla.contacts.model.contact.entity.Contacts;
import dev.senzalla.contacts.model.contact.module.ContactsMinimal;
import dev.senzalla.contacts.model.contact.module.ContactsSummarize;
import dev.senzalla.contacts.repository.ContactsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FindMultipleContactTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContactController contactController;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    void shouldOkFindMultipleContact() throws Exception {
        Mockito.when(contactsRepository.findByNameContact(Mockito.any(), Mockito.any())).thenReturn(Page.empty());
        ResultActions perform = mockMvc.perform(get("/contact", 0).param("Authorization", Tokens.tokenCreate).contentType(MediaType.APPLICATION_JSON));

        perform.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldOkFindMultipleContactWithAuthorization() {
        Mockito.when(contactsRepository.findByNameContact(Mockito.any(), Mockito.any())).thenReturn(new PageImpl<>(List.of(new Contacts())));
        var teste = contactController.findMultipleContact(Pageable.unpaged(), null, Tokens.tokenCreate);
        Assertions.assertInstanceOf(ContactsSummarize.class, Objects.requireNonNull(teste.getBody()).stream().findAny().orElseThrow());
    }

    @Test
    void shouldOkFindMultipleContactWithoutAuthorization() {
        Mockito.when(contactsRepository.findByNameContact(Mockito.any(), Mockito.any())).thenReturn(new PageImpl<>(List.of(new Contacts())));
        var teste = contactController.findMultipleContact(Pageable.unpaged(), null, null);
        Assertions.assertInstanceOf(ContactsMinimal.class, Objects.requireNonNull(teste.getBody()).stream().findAny().orElseThrow());
    }
}
