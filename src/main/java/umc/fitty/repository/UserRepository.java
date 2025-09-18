package umc.fitty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.fitty.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
