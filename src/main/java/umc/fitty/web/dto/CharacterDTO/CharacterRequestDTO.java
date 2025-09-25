package umc.fitty.web.dto.CharacterDTO;

import lombok.Getter;

public class CharacterRequestDTO {

    @Getter
    public static class CreateCharacterDTO {
        private String name;
        private String type;
    }
}
