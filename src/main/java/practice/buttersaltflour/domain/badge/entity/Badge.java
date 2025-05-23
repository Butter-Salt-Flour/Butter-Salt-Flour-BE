package practice.buttersaltflour.domain.badge.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.member.Youth.Youth;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "youth_id")
    private Youth youth;
}
