package practice.buttersaltflour.domain.bingoboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Quest {

    @Id
    @GeneratedValue
    @Column(name = "quest_id")
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private MissionType level;

    @Column(nullable = false)
    private boolean completed;

    private LocalDateTime completedAt;

    private String imageUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingoboard_id")
    private BingoBoard bingoBoard;

}
