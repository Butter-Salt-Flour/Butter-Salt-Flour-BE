package practice.buttersaltflour.domain.bingo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.member.Matching.Matching;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class BingoBoard {

    @Id
    @GeneratedValue
    @Column(name = "bingoboard_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BingoBoardLevel level;

    @JsonIgnore
    @OneToMany(mappedBy = "bingoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quest> quests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "bingoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matching> matchings = new ArrayList<>();
}
