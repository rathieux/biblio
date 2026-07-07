package biblio.dao;

import biblio.model.Auteur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IDAOAuteur implements PanacheRepositoryBase<Auteur, Integer> {

}
