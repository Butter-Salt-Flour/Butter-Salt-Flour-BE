package practice.buttersaltflour.domain.member.Senior;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.domain.member.Senior.DTO.SeniorResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SeniorService {

    private final SeniorRepository seniorRepository;
    @Transactional(readOnly = true)
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
