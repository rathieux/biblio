package biblio.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import biblio.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
    public Optional<Utilisateur> findByUsername(String username);
}
