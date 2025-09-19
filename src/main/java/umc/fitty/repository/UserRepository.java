package umc.fitty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.fitty.domain.AuthProvider;
import umc.fitty.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId);
}
