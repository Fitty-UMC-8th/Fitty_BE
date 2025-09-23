package umc.fitty.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.fitty.config.security.SecurityUtil;
import umc.fitty.web.dto.UserDTO.UserMeResponseDTO;
import umc.fitty.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "내 정보 조회",
        description = "현재 로그인한 사용자의 프로필 정보를 반환합니다.",
            security = { @SecurityRequirement(name = "bearerAuth") },
        responses = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = @Content(schema = @Schema(implementation = UserMeResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "인증 실패")
        }
    )
    @GetMapping("/users/me")
    public ResponseEntity<UserMeResponseDTO> getMyProfile() {
        Long userId = SecurityUtil.getCurrentUserId();
        return ResponseEntity.ok(userService.getMe(userId));
    }
}
