package biblio.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.dao.IDAOUtilisateur;
import biblio.model.Utilisateur;
import biblio.security.JwtUtils;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {
    @Autowired
    private IDAOUtilisateur utilisateurDao;

    @PostMapping()
    public Utilisateur ajouter(@RequestBody Utilisateur utilisateur) {
        utilisateur.setPassword(JwtUtils.encoder().encode(utilisateur.getPassword()));
        System.out.println("utilisateur=" + utilisateur.getUsername());
        System.out.println("password=" + utilisateur.getPassword());
        utilisateurDao.save(utilisateur);
        return utilisateur;
    }

}
