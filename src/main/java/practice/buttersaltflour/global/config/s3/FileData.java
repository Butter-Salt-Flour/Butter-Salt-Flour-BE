package practice.buttersaltflour.global.config.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileData {
    private String mime;
    private byte[] imageBytes;  // 인코딩하지 않은 원본 바이트 배열 저장
}
