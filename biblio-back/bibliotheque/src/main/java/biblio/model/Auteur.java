package biblio.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Auteur {
    private Integer id;
    private String nom;
    private String prenom;
    private String nationalite;

    public Auteur() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + "]";
    }

    
}



	
	
