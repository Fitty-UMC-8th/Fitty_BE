package umc.fitty.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.fitty.domain.common.BaseEntity;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String kakaoId;

    @Column(length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(length = 255)
    private String profileImageUrl;

    public void updateFromKakao(String email, String nickname, String profileImageUrl) {
        if (email != null) this.email = email;
        if (nickname != null) this.nickname = nickname;
        if (profileImageUrl != null) this.profileImageUrl = profileImageUrl;
    }
}