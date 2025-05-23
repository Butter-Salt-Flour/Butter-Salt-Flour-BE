package practice.buttersaltflour.domain.member.Youth;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.buttersaltflour.domain.badge.entity.Badge;
import practice.buttersaltflour.domain.member.Youth.DTO.YouthRequest;
import practice.buttersaltflour.domain.member.enumList.Gender;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Youth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long youthId;

    private String uid;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private int age;

    private String profileImage;

    @OneToMany(mappedBy = "youth", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<YouthBadge> youthBadges = new ArrayList<>();

    @Builder
    public Youth(String uid, String name, Gender gender, String phoneNumber, int age, String profileImage) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.profileImage = profileImage;
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
