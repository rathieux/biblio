package biblio.dto.response;

import biblio.model.Livre;

public record LivreResponse(int id, String titre, String resume, String annee, String auteur, String editeur, String collection) {
    public static LivreResponse convert(Livre livre) {
        return new LivreResponse(livre.getId(), livre.getTitre(), livre.getResume(),livre.getAnnee(), livre.getAuteur(), livre.getEditeur(), livre.getCollection());
    }

}
