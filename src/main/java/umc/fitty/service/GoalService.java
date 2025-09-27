package umc.fitty.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.fitty.domain.Goal;
import umc.fitty.domain.User;
import umc.fitty.domain.enums.GoalType;
import umc.fitty.repository.GoalRepository;
import umc.fitty.repository.UserRepository;
import umc.fitty.web.dto.GoalDTO.GoalCreateRequestDTO;
import umc.fitty.web.dto.GoalDTO.GoalResponseDTO;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Transactional
    public GoalResponseDTO create(Long currentUserId, GoalCreateRequestDTO req){
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LocalDate weekStart = resolveWeekStart();

        // 이미 존재하면 409 또는 덮어쓰기(택1). 여기서는 409.
        goalRepository.findByUserIdAndWeekStartDate(currentUserId, weekStart)
                .ifPresent(g -> { throw new IllegalStateException("이미 해당 주 목표가 존재합니다."); });

        validateByType(req); // 한 타입만 set

        Goal goal = Goal.builder()
                .user(user)
                .type(req.getType())
                .weekStartDate(weekStart)
                .targetCount( req.getType()==GoalType.COUNT    ? req.getTargetCount()    : null)
                .targetDistance(req.getType()==GoalType.DISTANCE ? req.getTargetDistance() : null)
                .targetTime(  req.getType()==GoalType.DURATION ? req.getTargetTime()     : null)
                .progressCount(0)
                .progressDistance(0d)
                .progressTime(0)
                .build();

        Goal saved = goalRepository.save(goal);
        return toResponse(saved);
    }

    private void validateByType(GoalCreateRequestDTO r){
        int filled = (r.getTargetCount()!=null?1:0) + (r.getTargetDistance()!=null?1:0) + (r.getTargetTime()!=null?1:0);
        if (filled != 1) throw new IllegalArgumentException("타입에 맞는 target 하나만 입력해야 합니다.");

        switch (r.getType()){
            case COUNT    -> require(r.getTargetCount()!=null && r.getTargetCount()>=0, "targetCount >= 0");
            case DISTANCE -> require(r.getTargetDistance()!=null && r.getTargetDistance()>=0, "targetDistance >= 0");
            case DURATION -> require(r.getTargetTime()!=null && r.getTargetTime()>=0, "targetTime >= 0");
        }
    }

    private void require(boolean cond, String msg){ if(!cond) throw new IllegalArgumentException(msg); }

    private LocalDate resolveWeekStart() {
        return LocalDate.now()
                .with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
    }

    private GoalResponseDTO toResponse(Goal g){
        return GoalResponseDTO.builder()
                .id(g.getId())
                .type(g.getType())
                .targetCount(g.getTargetCount())
                .targetDistance(g.getTargetDistance())
                .targetTime(g.getTargetTime())
                .progressCount(g.getProgressCount())
                .progressDistance(g.getProgressDistance())
                .progressTime(g.getProgressTime())
                .weekStartDate(g.getWeekStartDate())
                .summary(g.summary())
                .build();
    }
}
