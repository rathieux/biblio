package biblio.dao;

import biblio.model.Avis;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface IDAOAvis extends PanacheRepositoryBase<Avis,Integer> {
    
}
