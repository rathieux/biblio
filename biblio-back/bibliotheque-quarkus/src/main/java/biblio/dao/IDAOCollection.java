package biblio.dao;

import biblio.model.Collection;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface IDAOCollection extends PanacheRepositoryBase<Collection, Integer> {

}
