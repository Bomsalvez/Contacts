package dev.senzalla.contacts.controller.phonenumber;

import dev.senzalla.contacts.constants.Tokens;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeletePhonenumberTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhonenumberRepository mailRepository;

    @Test
    void shouldOkWhenDeletePhonenumber() throws Exception {
        final String urlTemplate = "/phone/{pkContact}";
        Mockito.doNothing().when(mailRepository).deletePhoneToContact(Mockito.anyLong(), Mockito.anyLong());

        ResultActions perform = mockMvc.perform(delete(urlTemplate, 1)
                .header("Authorization", Tokens.tokenCreate)
                .param("pkPhonenumber", "1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        perform.andDo(print()).andExpect(status().isNoContent());
    }
}
