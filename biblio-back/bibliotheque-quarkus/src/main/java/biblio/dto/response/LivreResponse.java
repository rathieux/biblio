package biblio.dto.response;


import biblio.model.Auteur;
import biblio.model.Collection;
import biblio.model.Editeur;
import biblio.model.Livre;

public class LivreResponse {
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

    public static LivreResponse convert(Livre livre) {
        LivreResponse resp = new LivreResponse();

        resp.setId(livre.getId());
        resp.setTitre(livre.getTitre());
        resp.setResume(livre.getResume());
        resp.setAnnee(livre.getAnnee());
        resp.setAuteur(livre.getAuteur());
        resp.setEditeur(livre.getEditeur());
        resp.setCollection(livre.getCollection());

        return resp;
    }


}
