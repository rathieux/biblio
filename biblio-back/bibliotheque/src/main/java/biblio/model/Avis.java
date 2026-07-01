package biblio.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int note;

    @Column(length = 500)
    private String commentaire;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Avis(Integer id, int note, String commentaire, LocalDate date, Livre livre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.date = date;
        this.livre = livre;
    }

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;

    public Avis() {
    }

    public Integer getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}