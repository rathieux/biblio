package biblio.dto.request;


import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateLivreRequest(@NotBlank String titre) {

}