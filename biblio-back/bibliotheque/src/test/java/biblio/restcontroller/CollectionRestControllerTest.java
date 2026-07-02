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

import biblio.dao.IDAOCollection;
import biblio.dao.IDAOUtilisateur;

@WebMvcTest(controllers = CollectionRestController.class)
public class CollectionRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOCollection collectionDao;

    @MockitoBean
    private IDAOUtilisateur utilisateurDao;

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        String url = "/api/collection";

        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(url)
        );

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());

        Mockito.verify(this.collectionDao).findAll();
    }

    @Test
    @WithMockUser
    void shouldDeleteByIdStatusOk() throws Exception {
        int id = 1;
        String url = "/api/collection/" + id;

        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(url)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()));
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.collectionDao).deleteById(id);
    }
}
