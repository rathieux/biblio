package biblio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import biblio.model.Livre;

public interface IDAOLivre extends JpaRepository<Livre, Integer> {

    @Query("select l from Livre l left join fetch l.avis where l.id = :idLivre")
    public Livre findByIdWithAvis(@Param("idLivre") Integer idLivre);

    @Query("select l from Livre l where l.auteur.id = :idAuteur")
    public List<Livre> findByAuteur(@Param("idAuteur") Integer idAuteur);

    @Query("select l from Livre l where l.editeur.id = :idEditeur")
    public List<Livre> findByEditeur(@Param("idEditeur") Integer idEditeur);

    @Query("select l from Livre l where l.collection.id = :idCollection")
    public List<Livre> findByCollection(@Param("idCollection") Integer idCollection);

    @Query("select l from Livre l where l.annee = :annee")
    public List<Livre> findByAnnee(@Param("annee") Integer annee);

}
