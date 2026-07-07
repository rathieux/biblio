package biblio.response;

import biblio.model.Collection;


public class CollectionResponse {

    private Integer id;
    private String nom;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

      public static CollectionResponse convert(Collection collection) {
        CollectionResponse resp = new CollectionResponse();

        resp.setId(collection.getId());
        resp.setNom(collection.getNom());

        return resp;
    }
}
