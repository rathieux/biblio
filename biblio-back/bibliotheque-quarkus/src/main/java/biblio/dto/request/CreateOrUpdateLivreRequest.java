package biblio.dto.request;



import biblio.model.Auteur;
import biblio.model.Collection;
import biblio.model.Editeur;

public class CreateOrUpdateLivreRequest {

    private Integer id;
    private String titre;
    private String resume;

    private Integer annee;
    private Auteur auteur;
    private Editeur editeur;
    private Collection collection;

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

    public Integer getAnnee(){
        return annee;
    }

    public void setAnnee(Integer annee){
        this.annee = annee;
    }

    public Auteur getAuteur(){
        return auteur;
    }

    public void setAuteur(Auteur auteur){
        this.auteur = auteur;
    }

    public Editeur getEditeur(){
        return editeur;
    }

    public void setEditeur(Editeur editeur){
        this.editeur = editeur;
    }

    public Collection getCollection(){
        return collection;
    }

    public void setCollection(Collection collection){
        this.collection = collection;
    }

}
