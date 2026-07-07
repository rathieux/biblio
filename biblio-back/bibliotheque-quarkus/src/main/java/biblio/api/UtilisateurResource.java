package biblio.api;

import biblio.dao.IDAOUtilisateur;
import biblio.model.Utilisateur;
import biblio.request.CreateUtilisateurRequest;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/utilisateur")
public class UtilisateurResource {

    private final IDAOUtilisateur utilisateurDao;

    public UtilisateurResource(IDAOUtilisateur utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @POST
    @Transactional
    public Utilisateur ajouter(CreateUtilisateurRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(request.getUsername());
        utilisateur.setPassword(BcryptUtil.bcryptHash(request.getPassword()));
        System.out.println("utilisateur=" + utilisateur.getUsername());
        System.out.println("password=" + utilisateur.getPassword());
        utilisateurDao.persist(utilisateur);
        return utilisateur;
    }

}
