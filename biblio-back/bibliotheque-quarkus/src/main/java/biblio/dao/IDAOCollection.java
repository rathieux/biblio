package biblio.dao;

import biblio.model.Collection;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IDAOCollection implements PanacheRepositoryBase<Collection, Integer> {

}
