package practice.buttersaltflour.domain.bingo.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import practice.buttersaltflour.domain.bingo.domain.BingoBoard;
import practice.buttersaltflour.domain.bingo.domain.Quest;

@Getter
@Builder
public class BingoBoardResponse {

    @Schema(description = "빙고 아이디", example = "1")
    private final Long id;

    @Schema(description = "빙고 난이도", example = "HIGH")
    private final String level;

    @Schema(description = "퀘스트 리스트")
    private final List<QuestResponse> quests;

    @Getter
    @Builder
    public static class QuestResponse {

        @Schema(description = "퀘스트 ID", example = "10")
        private final Long questId;

        @Schema(description = "퀘스트 설명", example = "친구에게 안부 묻기")
        private final String description;

        @Schema(description = "퀘스트 완료 여부", example = "false")
        private final boolean completed;

        @Schema(description = "퀘스트 위치 - 행", example = "1")
        private final int row;

        @Schema(description = "퀘스트 위치 - 열", example = "2")
        private final int column;

        @Schema(description = "퀘스트 이미지 URL", example = "https://example.com/image.jpg")
        private final String imageUrl;
    }

    public static BingoBoardResponse from(BingoBoard bingoBoard) {
        return BingoBoardResponse.builder()
                .id(bingoBoard.getId())
                .level(bingoBoard.getLevel().name())
                .quests(bingoBoard.getQuests().stream()
                        .map(BingoBoardResponse::mapToQuestResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    private static QuestResponse mapToQuestResponse(Quest quest) {
        return QuestResponse.builder()
                .questId(quest.getId())
                .description(quest.getDescription())
                .completed(quest.isCompleted())
                .row(quest.getRow())
                .column(quest.getColumn())
                .imageUrl(quest.getImageUrl())
                .build();
    }
}
