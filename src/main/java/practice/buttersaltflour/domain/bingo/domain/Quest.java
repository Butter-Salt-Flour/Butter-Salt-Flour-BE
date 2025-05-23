package practice.buttersaltflour.domain.bingo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Quest {

    @Id
    @GeneratedValue
    @Column(name = "quest_id")
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionType level;

    @Setter
    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private int questOrder;

    @Setter
    private LocalDateTime completedAt;

    @Setter
    private String imageUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingoboard_id")
    private BingoBoard bingoBoard;

}
