package dev.senzalla.contacts.controller.mail;

import dev.senzalla.contacts.constants.Tokens;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteMailTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailRepository mailRepository;

    @Test
    void shouldOkWhenDeleteMail() throws Exception {
        final String urlTemplate = "/mail/{pkContact}";
        Mockito.doNothing().when(mailRepository).deleteMailToContact(Mockito.anyLong(), Mockito.anyLong());

        ResultActions perform = mockMvc.perform(delete(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .param("pkMail", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"mail\":  \"mail@mail.com\"}]")
        );

        perform.andDo(print()).andExpect(status().isNoContent());
    }
}
