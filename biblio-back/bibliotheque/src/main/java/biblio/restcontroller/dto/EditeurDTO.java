package biblio.restcontroller.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import biblio.model.Editeur;

public class EditeurDTO {

	private Integer id;
	private String nom;
	private String pays;
	
	
	public static EditeurDTO convert(Editeur editeur) 
	{
		EditeurDTO editeurDTO = new EditeurDTO();
		BeanUtils.copyProperties(editeur, editeurDTO);
		return editeurDTO;
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

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	
}
