package quest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateEditeurRequest(@NotBlank String nom) {

}