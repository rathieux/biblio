package biblio.api;

import java.util.List;

import biblio.dao.IDAOAuteur;
import biblio.model.Auteur;
import biblio.request.CreateOrUpdateAuteurRequest;
import biblio.response.AuteurResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/auteur")
public class AuteurResource {

    private final IDAOAuteur auteurDao;

    public AuteurResource(IDAOAuteur auteurDao) {
        this.auteurDao = auteurDao;
    }

    @GET
    public List<AuteurResponse> chercherTous() {
        return this.auteurDao.findAll()
                .stream()
                .map(AuteurResponse::convert)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Auteur chercherParId(@PathParam("id") Integer id) {
        return auteurDao.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void supprimer(@PathParam("id") Integer id) {
        auteurDao.deleteById(id);
    }

    @POST
    @Transactional
    public Auteur ajouter(CreateOrUpdateAuteurRequest request) {

        Auteur auteur = new Auteur();
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        auteurDao.persist(auteur);
        return auteur;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public int modifier(@PathParam("id") Integer id, CreateOrUpdateAuteurRequest request) {
        Auteur auteur = this.auteurDao.findByIdOptional(id).orElseThrow(NotFoundException::new);

        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.auteurDao.persist(auteur);

        return id;
    }
}
