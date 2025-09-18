package umc.fitty.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.fitty.domain.common.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double targetDistance;
    private Double progressDistance;

    private Integer targetCount;
    private Integer progressCount;

    private Integer targetTime;
    private Integer progressTime;

    @Column(nullable = false)
    private LocalDate weekStartDate;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<RunRecord> runRecordList = new ArrayList<>();

    public void updateProgress(Double distance, Integer durationMin){
        this.progressDistance += distance;
        this.progressTime += durationMin;
        this.progressCount += 1;
    }


}
