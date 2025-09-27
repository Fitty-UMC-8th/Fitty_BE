package umc.fitty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.fitty.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
