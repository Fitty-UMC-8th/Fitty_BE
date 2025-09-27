package umc.fitty.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.fitty.apiPayload.ApiResponse;
import umc.fitty.converter.CommentConverter;
import umc.fitty.domain.Comment;
import umc.fitty.service.CommentService.CommentCommandService;
import umc.fitty.web.dto.CommentDTO.CommentRequestDTO;
import umc.fitty.web.dto.CommentDTO.CommentResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class CommentRestController {

    private final CommentCommandService commentCommandService;

    @PostMapping("/{recordId}/comments")
    public ApiResponse<CommentResponseDTO.CreateCommentResultDTO> createComment(
            @PathVariable(name = "recordId") Long recordId, @Valid @RequestBody CommentRequestDTO.CreateCommentDTO request) {


        Comment newComment = commentCommandService.createComment(recordId, request);

        return ApiResponse.success("댓글이 성공적으로 작성되었습니다.", CommentConverter.toCreateCommentResultDTO(newComment));
    }
}
