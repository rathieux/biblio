package biblio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 25, nullable = false)
    private String nom;
    //@OneToMany(mappedBy = "collection")
    //private List<Livre> livres;

    public Collection() {
    }

    public Collection(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

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

    @Override
    public String toString() {
        return "Collection [id=" + id + ", nom=" + nom + "]";
    }
}
