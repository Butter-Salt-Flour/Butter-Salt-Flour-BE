package practice.buttersaltflour.domain.quest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.bingoboard.domain.BingoBoard;

import java.time.LocalDateTime;

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
