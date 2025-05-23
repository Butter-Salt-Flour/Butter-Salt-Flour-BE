package practice.buttersaltflour.member.entity;

import jakarta.persistence.*;
import practice.buttersaltflour.member.entity.enumList.MatchStatus;

import java.time.LocalDateTime;

@Entity
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
