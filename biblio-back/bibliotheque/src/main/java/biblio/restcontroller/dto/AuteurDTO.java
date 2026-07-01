package biblio.restcontroller.dto;

import org.springframework.beans.BeanUtils;

import biblio.model.Auteur;

public class AuteurDTO {
    private Integer id;
	private String nom,prenom,nationalite;

    public static AuteurDTO convert(Auteur auteur) 
	{
		AuteurDTO auteurDTO = new AuteurDTO();
		BeanUtils.copyProperties(auteur, auteurDTO);
		return auteurDTO;
	}


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


}
