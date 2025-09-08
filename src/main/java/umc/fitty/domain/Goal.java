package umc.fitty.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.fitty.domain.common.BaseEntity;
import umc.fitty.domain.enums.GoalType;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "goal_uk",
                columnNames = {"user_id", "weekStartDate"}
        )
})
public class Goal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GoalType type;

    private Integer targetValue;

    private Integer progressValue;

    private LocalDate weekStartDate;


}
