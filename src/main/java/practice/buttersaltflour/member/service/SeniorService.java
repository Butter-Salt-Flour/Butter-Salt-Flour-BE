package practice.buttersaltflour.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.buttersaltflour.domain.member.entity.Senior;
import practice.buttersaltflour.member.controller.dto.SeniorResponseDto;
import practice.buttersaltflour.member.repository.SeniorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeniorService {

    private final SeniorRepository seniorRepository;

    public List<SeniorResponseDto> getAllSeniors() {
        List<Senior> seniors = seniorRepository.findAll();

        return seniors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SeniorResponseDto convertToDto(Senior senior) {
        return SeniorResponseDto.builder()
                .seniorId(senior.getSeniorId())
                .name(senior.getName())
                .age(senior.getAge())
                .address(senior.getAddress())
                .latitude(senior.getLatitude())
                .longitude(senior.getLongitude())
                .description(senior.getDescription())
                .profileImage(senior.getProfileImage())
                .build();
    }
}
