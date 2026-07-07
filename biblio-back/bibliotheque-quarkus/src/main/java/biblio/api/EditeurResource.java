package biblio.api;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import biblio.dao.IDAOEditeur;
import biblio.dto.request.CreateOrUpdateEditeurRequest;
import biblio.dto.response.EditeurResponse;
import biblio.model.Editeur;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/editeur")
public class EditeurResource {
    private static Logger log = LoggerFactory.getLogger(EditeurResource.class);
    private final IDAOEditeur repository;

    public EditeurResource(IDAOEditeur repository) {
        this.repository = repository;
    }

    @GET
    public List<EditeurResponse> findAll() {
        log.debug("Liste des éditeur ...");

        return this.repository.findAll().stream().map(EditeurResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public EditeurResponse findById(@PathParam("id") Integer id) {
        log.debug("Recherche de la éditeur {} ...", id);

        return EditeurResponse.convert(this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    @POST
    public Response create(CreateOrUpdateEditeurRequest request) {
        log.debug("Création d'un nouvelle éditeur ...");

        Editeur editeur = new Editeur();

        
        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        this.repository.persist(editeur);

        log.debug("Editeur créée !");

        return Response.status(Response.Status.CREATED)
                .entity(Map.of("id", editeur.getId()))
                .build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, CreateOrUpdateEditeurRequest request) {
        log.debug("Modification de la titre {} ...", id);

        Editeur editeur = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        this.repository.persist(editeur);

        log.debug("Editeur modifiée !");

        return Response.ok(Map.of("id", editeur.getId())).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        log.debug("Suppression de l'éditeur {} ...", id);

        this.repository.deleteById(id);

        log.debug("Editeur supprimée !");

        return Response.noContent().build();
    }
}
