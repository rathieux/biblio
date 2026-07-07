package biblio.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biblio.dao.IDAOLivre;
import biblio.dto.request.CreateOrUpdateLivreRequest;
import biblio.dto.response.LivreResponse;
import biblio.model.Livre;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/livre")
public class LivreResource {
    private static Logger log = LoggerFactory.getLogger(LivreResource.class);
    private final IDAOLivre livreDao;

    public LivreResource(IDAOLivre livreDao) {
        this.livreDao = livreDao;
    }

    @GET
    public List<LivreResponse> findAll() {
        log.debug("Liste des livres ...");

        return this.livreDao.findAll().stream().map(LivreResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public LivreResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche du livre {} ...", id);

        return LivreResponse.convert(this.livreDao.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(CreateOrUpdateLivreRequest request) {
        log.debug("Création d'un nouveaux livre ...");

        Livre livre = new Livre();

        livre.setTitre(request.getTitre());

        this.livreDao.persist(livre);

        log.debug("Livre créée !");

        return Response.status(Response.Status.CREATED)
                .entity(Map.of("id", livre.getId()))
                .build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, CreateOrUpdateLivreRequest request) {
        log.debug("Modification de la titre {} ...", id);

        Livre livre = this.livreDao.findByIdOptional(id).orElseThrow(NotFoundException::new);

        livre.setTitre(request.getTitre());

        this.livreDao.persist(livre);

        log.debug("Livre modifiée !");

        return Response.ok(Map.of("id", livre.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression du livre {} ...", id);

        this.livreDao.deleteById(id);

        log.debug("Livre supprimée !");

        return Response.noContent().build();
    }
}
