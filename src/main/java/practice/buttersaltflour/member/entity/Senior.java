package practice.buttersaltflour.member.entity;

import jakarta.persistence.*;
import lombok.*;
import practice.buttersaltflour.member.entity.enumList.Gender;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "senior")
public class Senior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seniorId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private String address;

    private Integer age;

    private String profileImage;

    @Column(length = 1000)
    private String description; // 한줄 소개

    // 💡 매칭 리스트 (1:N)
    @OneToMany(mappedBy = "senior", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matching> matchings;

}
