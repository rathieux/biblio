package biblio.dao;

import biblio.model.Livre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IDAOLivre implements PanacheRepositoryBase<Livre, Integer> {

}
