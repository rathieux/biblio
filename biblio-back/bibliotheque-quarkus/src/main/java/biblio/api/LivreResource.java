package quest.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import quest.dto.request.CreateOrUpdateLivreRequest;
import quest.dto.response.LivreResponse;
import quest.model.Livre;
import quest.repo.LivreRepository;

@Path("/api/livre")
public class LivreResource {
    private static Logger log = LoggerFactory.getLogger(LivreResource.class);
    private final LivreRepository repository;

    public LivreResource(LivreRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<LivreResponse> findAll() {
        log.debug("Liste des livres ...");

        return this.repository.findAll().stream().map(LivreResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public LivreResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche du livre {} ...", id);

        return LivreResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @GET
    @Path("/by-titre/{titre}")
    public LivreResponse findByLibelle(@PathParam("titre") String titre) {
        log.debug("Recherche du livre par titre {} ...", titre);

        return LivreResponse.convert(this.repository.findByLibelle(titre).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(@Valid CreateOrUpdateLivreRequest request) {
        log.debug("Création d'un nouveaux livre ...");

        Livre livre = new Livre();

        livre.setTitre(request.titre());

        this.repository.persist(livre);

        log.debug("Livre créée !");

        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", livre.getId()))
            .build()
        ;
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid CreateOrUpdateLivreRequest request) {
        log.debug("Modification de la titre {} ...", id);

        Livre livre = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        livre.setTitre(request.titre());

        this.repository.persist(livre);

        log.debug("Livre modifiée !");

        return Response.ok(Map.of("id", livre.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression du livre {} ...", id);

        this.repository.deleteById(id);

        log.debug("Livre supprimée !");

        return Response.noContent().build();
    }
}
