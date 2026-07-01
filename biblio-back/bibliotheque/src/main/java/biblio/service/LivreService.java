package biblio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dao.IDAOLivre;
import biblio.model.Livre;

@Service
public class LivreService {

    @Autowired
    IDAOLivre daoLivre;

    public List<Livre> getAll() {
        return daoLivre.findAll();
    }

    public Livre getById(Integer id) {
        Optional<Livre> opt = daoLivre.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else
            return null;
    }

    public void insert(Livre livre) {
        daoLivre.save(livre);
    }

    public void update(Livre livre) {
        daoLivre.save(livre);
    }

    public void delete(Integer id) {
        daoLivre.deleteById(id);
    }
}