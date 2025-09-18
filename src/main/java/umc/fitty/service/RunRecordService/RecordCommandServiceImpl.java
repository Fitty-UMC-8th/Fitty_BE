package umc.fitty.service.RunRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.fitty.apiPayload.code.ErrorCode;
import umc.fitty.apiPayload.exception.CustomException;
import umc.fitty.converter.RecordConverter;
import umc.fitty.domain.Goal;
import umc.fitty.domain.RunRecord;
import umc.fitty.domain.User;
import umc.fitty.repository.GoalRepository;
import umc.fitty.repository.RecordRepository;
import umc.fitty.repository.UserRepository;
import umc.fitty.web.dto.RunRecordDTO.RunRecordRequestDTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
@RequiredArgsConstructor
public class RecordCommandServiceImpl implements RecordCommandService {

    private final RecordRepository recordRepository;
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Override
    public RunRecord createRecord(RunRecordRequestDTO.CreateRecordDTO request) {
        RunRecord newRecord = RecordConverter.toRunRecord(request);

        User user = userRepository.findById(1L).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        LocalDate today = LocalDate.now();
        LocalDate weekMonday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        Goal goal = goalRepository.findByUserAndWeekStartDate(user, weekMonday).orElseThrow(() -> new CustomException(ErrorCode.GOAL_NOT_FOUND));

        goal.updateProgress(newRecord.getDistance(), newRecord.getDurationMin());

        newRecord.setUserAndGoal(user, goal);

        return recordRepository.save(newRecord);
    }
}
