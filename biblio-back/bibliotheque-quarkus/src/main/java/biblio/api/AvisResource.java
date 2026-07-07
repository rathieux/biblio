package biblio.api;

import java.util.List;

import biblio.dao.IDAOAvis;
import biblio.dao.IDAOLivre;
import biblio.model.Avis;
import biblio.request.CreateOrUpdateAvisRequest;
import biblio.response.AvisResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/avis")
public class AvisResource {

    private final IDAOAvis avisDao;
    private final IDAOLivre livreDao;

    public AvisResource(IDAOAvis avisDao, IDAOLivre livreDao) {
        this.livreDao = livreDao;
        this.avisDao = avisDao;
    }

    @GET
    public List<AvisResponse> chercherTous() {
        return this.avisDao.findAll()
                .stream()
                .map(AvisResponse::convert)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Avis chercherParId(@PathParam("id") Integer id) {
        return avisDao.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void supprimer(@PathParam("id") Integer id) {
        avisDao.deleteById(id);
    }

    @POST
    @Transactional
    public Avis ajouter(CreateOrUpdateAvisRequest request) {

        Avis avis = new Avis();
        avis.setNote(request.getNote());
        avis.setCommentaire(request.getCommentaire());
        avis.setDate(request.getDate());
        avis.setLivre(this.livreDao.findById(request.getLivre().getId()));

        avisDao.persist(avis);
        return avis;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public int modifier(@PathParam("id") Integer id, CreateOrUpdateAvisRequest request) {
        Avis avis = this.avisDao.findByIdOptional(id).orElseThrow(NotFoundException::new);

        avis.setNote(request.getNote());
        avis.setCommentaire(request.getCommentaire());
        avis.setDate(request.getDate());
        avis.setLivre(this.livreDao.findById(request.getLivre().getId()));

        this.avisDao.persist(avis);

        return id;
    }
}
