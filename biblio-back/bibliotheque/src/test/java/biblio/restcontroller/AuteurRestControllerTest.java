package biblio.restcontroller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import biblio.dao.IDAOAuteur;
import biblio.dao.IDAOUtilisateur;


@WebMvcTest(controllers = AuteurRestController.class)
public class AuteurRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOAuteur auteurDao;

    @MockitoBean
    private IDAOUtilisateur utilisateurDao;

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        String url = "/api/auteur";

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(url)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());

        Mockito.verify(this.auteurDao).findAll();
    }

    @Test
    @WithMockUser
    void shouldDeleteByIdStatusOk() throws Exception {
        int id = 1;
        String url = "/api/auteur/" + id;

        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(url)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()));
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.auteurDao).deleteById(id);
    }
}


