package biblio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.dao.IDAOLivre;
import biblio.restcontroller.dto.LivreDTO;
import biblio.model.Livre;

@RestController
@RequestMapping("/api/livre")
public class LivreRestController {

    @Autowired
    IDAOLivre daoLivre;

    @GetMapping
    public List<LivreDTO> chercherTous() {
        return daoLivre.findAll().stream().map(livre -> LivreDTO.convert(livre)).toList();
    }

    @GetMapping("/{id}")
    public LivreDTO chercherParId(@PathVariable Integer id) {
        return LivreDTO.convert(daoLivre.findById(id).orElse(null));
    }

    @GetMapping("/{id}/avis")
    public LivreDTO chercherParIdAvecAvis(@PathVariable Integer id) {
        return LivreDTO.convertWithAvis(daoLivre.findByIdWithAvis(id));
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        daoLivre.deleteById(id);
    }

    @PostMapping
    public LivreDTO ajouter(@RequestBody Livre livre) {
        return LivreDTO.convert(daoLivre.save(livre));
    }

    @PutMapping("/{id}")
    public LivreDTO modifier(@PathVariable Integer id, @RequestBody Livre livre) {
        livre.setId(id);
        return LivreDTO.convert(daoLivre.save(livre));
    }
}