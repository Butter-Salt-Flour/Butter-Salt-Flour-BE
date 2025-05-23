package practice.buttersaltflour.domain.bingoboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.member.entity.Matching;
import practice.buttersaltflour.domain.quest.domain.Quest;

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
    private int level;

    @JsonIgnore
    @OneToMany(mappedBy = "bingoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quest> quests = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matched_id", nullable = false, unique = true)
    private Matching matching;
}
