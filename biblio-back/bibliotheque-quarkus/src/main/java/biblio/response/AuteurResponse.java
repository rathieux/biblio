package biblio.response;

import biblio.model.Auteur;

public class AuteurResponse {
    private Integer id;
    private String nom;
    private String prenom;
    private String nationalite;

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public static AuteurResponse convert(Auteur auteur) {
        AuteurResponse resp = new AuteurResponse();

        resp.setId(auteur.getId());
        resp.setNom(auteur.getNom());
        resp.setPrenom(auteur.getPrenom());
        resp.setNationalite(auteur.getNationalite());

        return resp;
    }
}
