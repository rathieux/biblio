package biblio.response;

import java.time.LocalDate;

import biblio.model.Avis;
import biblio.model.Livre;

public class AvisResponse {

    private Integer id;
    private int note;
    private String commentaire;
    private LocalDate date;
    private Livre livreCustom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static AvisResponse convert(Avis avis) {
        AvisResponse resp = new AvisResponse();

        resp.setId(avis.getId());
        resp.setNote(avis.getNote());
        resp.setCommentaire(avis.getCommentaire());
        resp.setDate(avis.getDate());

        Livre chien = new Livre();
        chien.setTitre(avis.getLivre().getTitre());
        chien.setId(avis.getLivre().getId());
        resp.setLivreCustom(chien);

        return resp;
    }

    public Livre getLivreCustom() {
        return livreCustom;
    }

    public void setLivreCustom(Livre livreCustom) {
        this.livreCustom = livreCustom;
    }
}
