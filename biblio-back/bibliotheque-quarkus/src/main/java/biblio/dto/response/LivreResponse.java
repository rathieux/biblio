package biblio.dto.response;

import biblio.model.Editeur;
import biblio.model.Livre;

public class LivreResponse {
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

    public static LivreResponse convert(Livre livre) {
        LivreResponse resp = new LivreResponse();

        resp.setId(livre.getId());
        resp.setTitre(livre.getTitre());
        resp.setResume(livre.getResume());
        //resp.setAnnee(livre.getAnnee());
        //resp.setAuteur(livre.getAuteur());
        //resp.setEditeur(livre.getEditeur());
        //resp.setCollection(livre.getCollection());

        return resp;
    }


}
