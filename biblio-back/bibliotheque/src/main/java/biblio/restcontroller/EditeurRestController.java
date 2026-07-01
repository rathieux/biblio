package biblio.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import biblio.restcontroller.dto.EditeurDTO;
import biblio.dao.IDAOEditeur;
import biblio.model.Editeur;


@RestController
@RequestMapping("/api/editeur")
public class EditeurRestController {


	@Autowired
	IDAOEditeur daoEditeur;
	
	
	@GetMapping
	public List<EditeurDTO> chercherTous()  
	{
		return daoEditeur.findAll().stream().map(editeur->EditeurDTO.convert(editeur)).toList();	
	}
	
	@GetMapping("/{id}")
	public EditeurDTO chercherParId(@PathVariable Integer id)  
	{
		return EditeurDTO.convert((Editeur) daoEditeur.findById(id).orElse(null));
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)  
	{
		daoEditeur.deleteById(id);
	}
	
	@PostMapping
	public EditeurDTO ajouter(@RequestBody Editeur editeur)  
	{
		return EditeurDTO.convert(daoEditeur.save(editeur));
	}
	
	@PutMapping("/{id}")
	public EditeurDTO modifier(@PathVariable Integer id,@RequestBody Editeur editeur)  
	{
		editeur.setId(id);
		return EditeurDTO.convert(daoEditeur.save(editeur));
	}
	
}
	