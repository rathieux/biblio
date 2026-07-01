package biblio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import biblio.dao.IDAOAvis;
import biblio.restcontroller.dto.AvisDTO;
import biblio.model.Avis;

@RestController
@RequestMapping("/api/avis")
// @CrossOrigin("http://localhost:4200")
public class AvisRestController {

    @Autowired
    private IDAOAvis daoAvis;

    @GetMapping
    public List<AvisDTO> chercherTous() {
        return daoAvis.findAll().stream().map(avis -> AvisDTO.convert(avis)).toList();
    }

    @GetMapping("/{id}")
    public AvisDTO chercherParId(@PathVariable Integer id) {
        return AvisDTO.convert(daoAvis.findById(id).orElse(null));
    }

    @PostMapping
    public AvisDTO ajouter(@RequestBody Avis avis) {
        return AvisDTO.convert(daoAvis.save(avis));
    }

    @PutMapping("/{id}")
    public AvisDTO modifier(@PathVariable Integer id, @RequestBody Avis avis) {
        avis.setId(id);
        return AvisDTO.convert(daoAvis.save(avis));
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        daoAvis.deleteById(id);
    }
}