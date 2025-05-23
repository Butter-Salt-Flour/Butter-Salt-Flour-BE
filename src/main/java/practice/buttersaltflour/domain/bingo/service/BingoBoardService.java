package practice.buttersaltflour.domain.bingo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.domain.bingo.controller.response.BingoBoardResponse;
import practice.buttersaltflour.domain.bingo.domain.BingoBoard;
import practice.buttersaltflour.domain.bingo.repository.BingoBoardRepository;
import practice.buttersaltflour.domain.member.entity.Matching;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BingoBoardService {

    private final BingoBoardRepository bingoBoardRepository;

    public BingoBoardResponse findByMatching(Matching matching) {
        BingoBoard bingoBoard = bingoBoardRepository.findByMatching(matching);
        return BingoBoardResponse.from(bingoBoard);
    }
}
