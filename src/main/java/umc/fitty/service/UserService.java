package umc.fitty.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.fitty.domain.*;
import umc.fitty.repository.UserRepository;

@Service
@RequiredArgsConstructor
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
}
