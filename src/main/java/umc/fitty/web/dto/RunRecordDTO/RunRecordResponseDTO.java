package umc.fitty.web.dto.RunRecordDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RunRecordResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecordResultDTO{

        private Long recordId;
        private LocalDateTime createdAt;
    }
}
