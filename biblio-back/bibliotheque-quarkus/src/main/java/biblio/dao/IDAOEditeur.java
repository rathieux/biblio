package biblio.dao;

import biblio.model.Editeur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IDAOEditeur implements PanacheRepositoryBase<Editeur,Integer> {

}
