package biblio.restcontroller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import biblio.dao.IDAOUtilisateur;
import biblio.model.Utilisateur;

@WebMvcTest(controllers = UtilisateurRestController.class)
public class UtilisateurRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOUtilisateur utilisateurDao;

    @Test
    @WithMockUser
    void shouldAddStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername("test");
        utilisateur.setPassword("test");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/utilisateur")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(utilisateur)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(utilisateurDao).save(any());
    }
}
