package practice.buttersaltflour.domain.badge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.buttersaltflour.domain.badge.repository.BadgeRepository;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository repository;

    @Override
    public void getBadges() {

    }
}
