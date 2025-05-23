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

    @Operation(summary = "퀘스트 이미지 조회", description = "S3에 업로드된 퀘스트 인증 이미지 URL을 조회합니다.")
    @GetMapping("/{questId}/image")
    public ResponseEntity<String> getQuestImage(
            @PathVariable Long questId
    ) {
        String imageUrl = questService.getImageUrl(questId);
        if (imageUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imageUrl);
    }

    @Operation(summary = "퀘스트 이미지 판별 결과 조회", description = "쿼리스트링으로 받은 이미지 URL을 기반으로 판별 결과를 조회합니다.")
    @GetMapping("/image/answer")
    public ResponseEntity<Boolean> getQuestImage(
            @RequestParam String imageUrl
    ) {
        boolean result = questService.getImageRecognitionResult(imageUrl);
        return ResponseEntity.ok(result);
    }

}
