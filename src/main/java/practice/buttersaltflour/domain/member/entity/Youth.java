package practice.buttersaltflour.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Youth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String uid;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private String address;

    private int age;

    private String profileImage;


    @Builder
    public Youth(String name, Gender gender, String phoneNumber, String address, int age, String profileImage) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.profileImage = profileImage;
    }

    public Youth(String uid) {
        this.uid = uid;
    }

}
