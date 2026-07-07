package biblio.api;

import java.util.List;

import biblio.dao.IDAOCollection;
import biblio.model.Collection;
import biblio.request.CreateOrUpdateCollectionRequest;
import biblio.response.CollectionResponse;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/collection")
public class CollectionResource {

    private final IDAOCollection collectionDao;

    public CollectionResource(IDAOCollection collectionDao) {
        this.collectionDao = collectionDao;
    }

    @GET
    public List<CollectionResponse> chercherTous() {
        return this.collectionDao.findAll()
                .stream()
                .map(CollectionResponse::convert)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Collection chercherParId(@PathParam("id") Integer id) {
        return collectionDao.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void supprimer(@PathParam("id") Integer id) {
        collectionDao.deleteById(id);
    }

    @POST
    @Transactional
    public Collection ajouter(CreateOrUpdateCollectionRequest request) {

        Collection collection = new Collection();
        collection.setNom(request.getNom());
        collectionDao.persist(collection);
        return collection;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public int modifier(@PathParam("id") Integer id, CreateOrUpdateCollectionRequest request) {
        Collection collection = this.collectionDao.findByIdOptional(id).orElseThrow(NotFoundException::new);

        collection.setNom(request.getNom());

        this.collectionDao.persist(collection);

        return id;
    }
}
