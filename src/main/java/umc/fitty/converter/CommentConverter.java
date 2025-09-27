package umc.fitty.converter;

import umc.fitty.domain.Comment;
import umc.fitty.web.dto.CommentDTO.CommentRequestDTO;
import umc.fitty.web.dto.CommentDTO.CommentResponseDTO;

public class CommentConverter {

    public static Comment toComment(CommentRequestDTO.CreateCommentDTO request) {
        return Comment.builder()
                .content(request.getContent())
                .build();
    }

    public static CommentResponseDTO.CreateCommentResultDTO toCreateCommentResultDTO(Comment comment) {
        return CommentResponseDTO.CreateCommentResultDTO.builder()
                .commentId(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
