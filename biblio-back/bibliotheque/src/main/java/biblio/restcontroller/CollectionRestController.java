package biblio.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.dao.IDAOCollection;
import biblio.model.Collection;

@RestController
@RequestMapping("/api/collection")
// @CrossOrigin("http://localhost:4200")
public class CollectionRestController {

    @Autowired
    IDAOCollection collectionDao;

    @GetMapping
    public List<Collection> chercherTous() {
        return collectionDao.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Collection> chercherParId(@PathVariable Integer id) {
        return collectionDao.findById(id);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Integer id) {
        collectionDao.deleteById(id);
    }

    @PostMapping
    public Collection ajouter(@RequestBody Collection collection) {
        collectionDao.save(collection);
        return collection;
    }

    @PutMapping("/{id}")
    public Collection modifier(@PathVariable Integer id, @RequestBody Collection collection) {
        collection.setId(id);
        collectionDao.save(collection);
        return collection;
    }
}
