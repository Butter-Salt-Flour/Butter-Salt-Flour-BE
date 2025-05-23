package practice.buttersaltflour.domain.bingo.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import practice.buttersaltflour.domain.bingo.domain.Quest;
import practice.buttersaltflour.domain.bingo.repository.QuestRepository;
import practice.buttersaltflour.domain.gemini.service.GeminiService;
import practice.buttersaltflour.global.config.s3.FileData;
import practice.buttersaltflour.global.config.s3.S3Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestService {

    private final QuestRepository questRepository;
    private final S3Service s3Service;
    private final GeminiService geminiService;

    @Transactional
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

    public String getImageUrl(Long questId) {
        Optional<Quest> questOptional = questRepository.findById(questId);
        if (questOptional.isEmpty()) {
            return null;
        }
        Quest quest = questOptional.get();
        return quest.getImageUrl();
    }

    public boolean getImageRecognitionResult(String imageUrl) {
        FileData fileData = s3Service.getImageFileData(imageUrl);
        return geminiService.sendRequestWithImage(fileData);
    }
}