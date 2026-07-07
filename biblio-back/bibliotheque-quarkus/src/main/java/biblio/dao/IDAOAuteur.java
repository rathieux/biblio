package biblio.dao;

import biblio.model.Auteur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface IDAOAuteur extends PanacheRepositoryBase<Auteur, Integer> {

}
