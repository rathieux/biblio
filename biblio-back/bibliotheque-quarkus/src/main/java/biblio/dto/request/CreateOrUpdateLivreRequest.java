package quest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateLivreRequest(@NotBlank String titre) {

}