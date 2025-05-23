package practice.buttersaltflour.domain.badge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.buttersaltflour.domain.badge.DTO.BadgeRequest;
import practice.buttersaltflour.domain.badge.entity.Badge;
import practice.buttersaltflour.domain.badge.repository.BadgeRepository;
import practice.buttersaltflour.domain.member.Youth.Youth;
import practice.buttersaltflour.domain.member.Youth.YouthRepository;
import practice.buttersaltflour.domain.member.exception.MemberException;
import util.execption.ErrorCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository repository;
    private final YouthRepository youthRepository;

    @Override
    public void getBadges() {

    }


}
