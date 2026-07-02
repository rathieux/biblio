package biblio.restcontroller;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import biblio.model.Editeur;
import biblio.dao.IDAOEditeur;
import biblio.dao.IDAOUtilisateur;


@WebMvcTest(controllers = EditeurRestController.class)
public class EditeurRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOEditeur repository;
    
    @MockitoBean
    private IDAOUtilisateur utilisateurDao;

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        // given
        String url = "/api/editeur";

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(url)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());

        Mockito.verify(this.repository).findAll();
    }

    @Test
    @WithMockUser
    void shouldDeleteByIdStatusOk() throws Exception {
        // given
        int id = 1;
        String url = "/api/editeur/" + id;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .delete(url)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repository).deleteById(id);
    }


}
