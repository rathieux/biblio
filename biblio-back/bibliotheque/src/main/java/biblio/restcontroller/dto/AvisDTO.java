package biblio.restcontroller.dto;

import java.time.LocalDate;

import org.hibernate.dialect.function.AvgFunction;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import biblio.model.Avis;

public class AvisDTO {

    private Integer id;
    private int note;
    private String commentaire;
    	
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private LivreDTO livreCustom;


    public AvisDTO() {
    }

    public static AvisDTO convert(Avis avis) 
	{
        
		AvisDTO avisDTO = new AvisDTO();
		BeanUtils.copyProperties(avis, avisDTO);
		return avisDTO;
	}

    public static AvisDTO convertWithLivre(Avis avis) {
        AvisDTO avisDTO = convert(avis);
        avisDTO.livreCustom = LivreDTO.convert(avis.getLivre());
        return avisDTO;
    }


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
}