package umc.fitty.service.CharacterService;

import umc.fitty.domain.Character;
import umc.fitty.web.dto.CharacterDTO.CharacterRequestDTO;

public interface CharacterCommandService {

    Character createCharacter(CharacterRequestDTO.CreateCharacterDTO request);
}
