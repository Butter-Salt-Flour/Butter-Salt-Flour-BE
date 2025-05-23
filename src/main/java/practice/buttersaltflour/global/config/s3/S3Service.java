package practice.buttersaltflour.global.config.s3;

import java.io.InputStream;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Component
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    public String uploadToProfileImageFolder(MultipartFile file) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"));


        String uniqueFileName = "bingoImage/" + "_" + currentTime;

        try {
            String tempFileName = uniqueFileName.replace("/", "_");
            Path tempFile = Files.createTempFile("temp-", tempFileName);

            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(uniqueFileName)
                            .contentType(file.getContentType())
                            .build(),
                    tempFile
            );

            return generateFileUrl(uniqueFileName);

        } catch (Exception e) {
            throw new IllegalArgumentException("S3 파일 업로드 실패");
        }
    }

    public FileData getImageFileData(String key) {
        try {
            // S3에서 메타데이터 가져오기 (contentType 포함)
            HeadObjectResponse metadata = s3Client.headObject(
                    HeadObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build()
            );
            String contentType = metadata.contentType();

            // 이미지 데이터 가져오기
            try (InputStream inputStream = s3Client.getObject(
                    GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build()
            )) {
                byte[] imageBytes = inputStream.readAllBytes();
                return new FileData(contentType, imageBytes);
            }

        } catch (Exception e) {
            throw new RuntimeException("S3에서 이미지 불러오기 실패", e);
        }
    }

    private String generateFileUrl(String uniqueFileName) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + uniqueFileName;
    }

}
