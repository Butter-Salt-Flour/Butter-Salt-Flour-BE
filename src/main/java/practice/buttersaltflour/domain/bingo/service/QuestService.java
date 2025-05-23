package practice.buttersaltflour.domain.bingo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practice.buttersaltflour.domain.bingo.domain.Quest;
import practice.buttersaltflour.domain.bingo.repository.QuestRepository;
import practice.buttersaltflour.global.config.s3.S3Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;
    private final S3Service s3Service;

    public String uploadQuestImage(Long questId, MultipartFile file) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new NoSuchElementException("해당 퀘스트가 존재하지 않습니다."));

        String imageUrl = s3Service.uploadToProfileImageFolder(file);
        quest.setImageUrl(imageUrl);
        quest.setCompleted(true);
        quest.setCompletedAt(java.time.LocalDateTime.now());

        questRepository.save(quest);
        return imageUrl;
    }
}