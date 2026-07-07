package biblio.dto.request;

public class CreateOrUpdateEditeurRequest {

    private Integer Id;
	private String nom;
	private String pays;

    public Integer getId(){
        return Id;
    }

    public void setId(Integer Id){
        this.Id = Id;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPays(){
        return pays;
    }

    public void setPays(String pays){
        this.pays = pays;
    }

}
