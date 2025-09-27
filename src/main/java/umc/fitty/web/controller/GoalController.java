package umc.fitty.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.fitty.config.security.SecurityUtil;
import umc.fitty.service.GoalService;
import umc.fitty.web.dto.GoalDTO.GoalCreateRequestDTO;
import umc.fitty.web.dto.GoalDTO.GoalResponseDTO;

@Tag(name = "Goal")
@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @Operation(summary="목표 생성 (주간 1개 제한)")
    @PostMapping
    public ResponseEntity<GoalResponseDTO> create(@Valid @RequestBody GoalCreateRequestDTO req){
        Long userId = SecurityUtil.getCurrentUserId();
        return ResponseEntity.ok(goalService.create(userId, req));
    }
}
