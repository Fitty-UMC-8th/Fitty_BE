package umc.fitty.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.fitty.apiPayload.ApiResponse;
import umc.fitty.converter.CharacterConverter;
import umc.fitty.domain.Character;
import umc.fitty.service.CharacterService.CharacterCommandService;
import umc.fitty.web.dto.CharacterDTO.CharacterRequestDTO;
import umc.fitty.web.dto.CharacterDTO.CharacterResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/me/character")
public class CharacterRestController {

    private final CharacterCommandService characterCommandService;

    @PostMapping
    public ApiResponse<CharacterResponseDTO.CreateCharacterResultDTO> createCharacter(@RequestBody CharacterRequestDTO.CreateCharacterDTO request) {
        Character character = characterCommandService.createCharacter(request);
        return ApiResponse.success("캐릭터가 성공적으로 생성되었습니다.", CharacterConverter.toCharacterResultDTO(character));
    }
}
