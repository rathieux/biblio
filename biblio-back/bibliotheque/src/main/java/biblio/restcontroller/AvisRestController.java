package biblio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import biblio.dao.IDAOAvis;
import biblio.model.Avis;
import biblio.restcontroller.dto.AvisDTO;

@RestController
@RequestMapping("/api/avis")
public class AvisRestController {

    @Autowired
    private IDAOAvis daoAvis;

    @GetMapping
    public List<AvisDTO> chercherTous() {
        return daoAvis.findAll()
                .stream()
                .map(AvisDTO::convertWithLivre)
                .toList();
    }

    @GetMapping("/{id}")
    public AvisDTO chercherParId(@PathVariable Integer id) {
        return AvisDTO.convertWithLivre(
                daoAvis.findById(id).orElse(null)
        );
    }

    @PostMapping
    public AvisDTO ajouter(@RequestBody Avis avis) {
        return AvisDTO.convertWithLivre(
                daoAvis.save(avis)
        );
    }

    @PutMapping("/{id}")
    public AvisDTO modifier(@PathVariable Integer id, @RequestBody Avis avis) {
        avis.setId(id);
        return AvisDTO.convertWithLivre(
                daoAvis.save(avis)
        );
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        daoAvis.deleteById(id);
    }
}