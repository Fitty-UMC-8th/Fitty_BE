package umc.fitty.converter;

import umc.fitty.domain.Character;
import umc.fitty.domain.enums.CharacterType;
import umc.fitty.web.dto.CharacterDTO.CharacterRequestDTO;
import umc.fitty.web.dto.CharacterDTO.CharacterResponseDTO;

public class CharacterConverter {

    public static Character toCharacter(CharacterRequestDTO.CreateCharacterDTO request){

        CharacterType characterType = CharacterType.valueOf(request.getType().toUpperCase());

        return Character.builder()
                .name(request.getName())
                .type(characterType)
                .build();
    }

    public static CharacterResponseDTO.CreateCharacterResultDTO toCharacterResultDTO(Character character){
        return CharacterResponseDTO.CreateCharacterResultDTO.builder()
                .characterId(character.getId())
                .createdAt(character.getCreatedAt())
                .build();
    }
}
