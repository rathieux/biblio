package biblio.dao;

import biblio.model.Avis;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IDAOAvis implements PanacheRepositoryBase<Avis,Integer> {
    
}
