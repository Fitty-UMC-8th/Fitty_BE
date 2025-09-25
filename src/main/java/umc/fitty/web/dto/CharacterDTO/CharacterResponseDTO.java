package umc.fitty.web.dto.CharacterDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CharacterResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCharacterResultDTO{

            private Long characterId;
            private LocalDateTime createdAt;

    }
}
