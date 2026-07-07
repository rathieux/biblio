package biblio.dto.request;

public class CreateOrUpdateLivreRequest {

    private Integer id;
    private String titre;
    private String resume;

    private String annee;
    private String auteur;
    private String editeur;
    private String collection;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitre(){
        return titre;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }

    public String getResume(){
        return resume;
    }

    public void setResume(String resume){
        this.resume = resume;
    }

    public String getAnnee(){
        return annee;
    }

    public void setAnnee(String annee){
        this.annee = annee;
    }

    public String getAuteur(){
        return auteur;
    }

    public void setAuteur(String auteur){
        this.auteur = auteur;
    }

    public String getEditeur(){
        return editeur;
    }

    public void setEditeur(String editeur){
        this.editeur = editeur;
    }

    public String getCollection(){
        return collection;
    }

    public void setCollection(String collection){
        this.collection = collection;
    }

}
