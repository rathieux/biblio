package biblio.dao;


import biblio.model.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IDAOUtilisateur implements PanacheRepositoryBase<Utilisateur, Integer> {
}
