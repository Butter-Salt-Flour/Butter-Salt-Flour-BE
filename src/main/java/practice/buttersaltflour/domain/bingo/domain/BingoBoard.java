package practice.buttersaltflour.domain.bingo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.member.entity.Matching;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class BingoBoard {

    @Id
    @GeneratedValue
    @Column(name = "bingoboard_id")
    private Long id;

    @Column(nullable = false)
    private BingoBoardLevel level;

    @JsonIgnore
    @OneToMany(mappedBy = "bingoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quest> quests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "bingoBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matching> matchings = new ArrayList<>();
}
