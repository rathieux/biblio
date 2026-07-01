package biblio.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import biblio.dao.IDAOUtilisateur;
import biblio.model.Utilisateur;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private IDAOUtilisateur utilisateurDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String token = authHeader.substring(7);
            String login = JwtUtils.validate(token);
            Optional<Utilisateur> utilisateur = this.utilisateurDao.findByUsername(login);

            if (utilisateur.isPresent()) {
                List<GrantedAuthority> authorities = new ArrayList<>();


                Authentication auth = new UsernamePasswordAuthenticationToken(login, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // On pense à passer à la suite
        filterChain.doFilter(request, response);
    }
}
