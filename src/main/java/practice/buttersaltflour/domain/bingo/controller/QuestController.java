package practice.buttersaltflour.domain.bingo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practice.buttersaltflour.domain.bingo.service.QuestService;

@Tag(name = "Quest", description = "퀘스트 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quest")
public class QuestController {

    private final QuestService questService;

    @Operation(summary = "퀘스트 이미지 업로드", description = "S3에 퀘스트 인증 이미지를 업로드합니다.")
    @PostMapping("/{questId}/image")
    public ResponseEntity<String> uploadQuestImage(
            @PathVariable Long questId,
            @RequestPart MultipartFile file
    ) {
        String uploadedUrl = questService.uploadQuestImage(questId, file);
        return ResponseEntity.ok(uploadedUrl);
    }
}
