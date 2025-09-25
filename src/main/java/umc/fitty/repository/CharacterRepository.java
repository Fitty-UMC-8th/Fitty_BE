package umc.fitty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.fitty.domain.Character;
import umc.fitty.domain.User;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    boolean existsByUser(User user);
}
