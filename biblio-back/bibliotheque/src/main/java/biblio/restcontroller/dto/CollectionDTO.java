package biblio.restcontroller.dto;

public class CollectionDTO {

    private Integer id;
    private String nom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfos() {
        return nom;
    }

    public void setInfos(String nom) {
        this.nom = nom;
    }

}
