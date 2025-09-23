package umc.fitty.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.fitty.domain.*;
import umc.fitty.repository.UserRepository;
import umc.fitty.web.dto.UserDTO.UserMeResponseDTO;
import umc.fitty.web.dto.UserDTO.UserMeUpdateRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User upsertFromKakao(String kakaoId, String email, String nickname, String profileImageUrl) {
        return userRepository.findByEmail(email)
                .map(u -> {
                    // 닉네임/프로필 최신화
                    u.updateFromKakao(email, nickname, profileImageUrl);
                    return u;
                })
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .kakaoId(kakaoId)
                                .email(email)
                                .nickname(nickname != null && !nickname.isBlank() ? nickname : ("카카오사용자_" + kakaoId))
                                .profileImageUrl(profileImageUrl)
                                .build()
                ));
    }

    public UserMeResponseDTO getMe(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));
        return toResponse(user);
    }

    @Transactional
    public UserMeResponseDTO updateMe(Long userId, UserMeUpdateRequestDTO req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));

        user.updateProfile(req.getNickname(), req.getProfileImageUrl());
        return toResponse(user);
    }

    private UserMeResponseDTO toResponse(User user) {
        return UserMeResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getEffectiveNickname())
                .profileImageUrl(user.getEffectiveProfileImageUrl())
                .build();
    }
}
