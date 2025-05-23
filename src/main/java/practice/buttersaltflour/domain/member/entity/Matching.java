package practice.buttersaltflour.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import practice.buttersaltflour.domain.bingoboard.domain.BingoBoard;
import practice.buttersaltflour.domain.member.entity.enumList.MatchStatus;

import java.time.LocalDateTime;

@Entity
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matched_id")
    private Long matchId;

    @Enumerated(EnumType.STRING)
    private MatchStatus isMatched;

    private LocalDateTime matchedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senior_id")
    private Senior senior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youth_id")
    private Youth youth;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingoboard_id", nullable = false, unique = true)
    private BingoBoard bingoBoard;

}
