package umc.fitty.web.dto.UserDTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMeResponseDTO {
    private Long id;
    private String email;
    private String nickname;
    private String profileImageUrl;
}