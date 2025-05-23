package practice.buttersaltflour.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.badge.entity.Badge;
import practice.buttersaltflour.domain.member.controller.dto.YouthRequest;
import practice.buttersaltflour.domain.member.entity.enumList.Gender;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Youth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youth_id")
    private Long YouthId;

    private String uid;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private int age;

    private String profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @Builder
    public Youth(String name, Gender gender, String phoneNumber, int age, String profileImage, Badge badge) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.profileImage = profileImage;
        this.badge = badge;
    }


    public Youth(String uid, String profileImage) {
        this.uid = uid;
        this.profileImage = profileImage;
    }

    public void resister(YouthRequest youthRequest) {
        this.name = youthRequest.getName();
        this.gender = youthRequest.getGender();
        this.phoneNumber = youthRequest.getPhoneNumber();
        this.age = youthRequest.getAge();
    }
}
