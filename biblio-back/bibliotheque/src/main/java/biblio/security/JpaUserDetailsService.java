package biblio.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import biblio.dao.IDAOUtilisateur;
import biblio.model.Utilisateur;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOUtilisateur utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = this.utilisateurDao.findByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return User.builder()
                .username(username)
                .password(utilisateur.get().getPassword())
                .build();

    }
}
