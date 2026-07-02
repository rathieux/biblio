package biblio.restcontroller;

import java.util.Optional;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import biblio.dao.IDAOLivre;
import biblio.dao.IDAOUtilisateur;
import biblio.model.Livre;

@WebMvcTest(controllers = LivreRestController.class)
public class LivreRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOLivre daoLivre;

    @MockitoBean
    private IDAOUtilisateur utilisateurDao;

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        String url = "/api/livre";
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(url));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());

        Mockito.verify(this.daoLivre).findAll();
    }

    @Test
    @WithMockUser
    void shouldFindByIdStatusOk() throws Exception {
        int id = 1;
        String url = "/api/livre/" + id;
        Livre livre = new Livre();
        livre.setId(id);

        Mockito.when(this.daoLivre.findById(id)).thenReturn(Optional.of(livre));
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(url));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());

        Mockito.verify(this.daoLivre).findById(id);
    }

    @Test
    @WithMockUser
    void shouldFindByIdWithAvisStatusOk() throws Exception {
        int id = 1;
        String url = "/api/livre/" + id + "/avis";
        Livre livre = new Livre();
        livre.setId(id);
        Mockito.when(this.daoLivre.findByIdWithAvis(id)).thenReturn(livre);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(url));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists());

        Mockito.verify(this.daoLivre).findByIdWithAvis(id);
    }

    @Test
    @WithMockUser
    void shouldDeleteByIdStatusOk() throws Exception {
        int id = 1;
        String url = "/api/livre/" + id;
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(url)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()));
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.daoLivre).deleteById(id);
    }

    @Test
    @WithMockUser
    void shouldAddStatusOk() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Livre livre = new Livre();
        livre.setTitre("test");

        Mockito.when(this.daoLivre.save(Mockito.any())).thenReturn(livre);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/livre")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livre)));
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.daoLivre).save(Mockito.any());
    }

    @Test
    @WithMockUser
    void shouldUpdateStatusOk() throws Exception {
        int id = 1;
        String url = "/api/livre/" + id;

        ObjectMapper objectMapper = new ObjectMapper();

        Livre livre = new Livre();
        livre.setId(id);
        livre.setTitre("test modifié");

        Mockito.when(this.daoLivre.save(Mockito.any())).thenReturn(livre);
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders.put(url)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livre)));
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.daoLivre).save(Mockito.any());
    }
}