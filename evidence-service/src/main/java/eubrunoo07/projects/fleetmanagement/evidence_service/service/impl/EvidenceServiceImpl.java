package eubrunoo07.projects.fleetmanagement.evidence_service.service.impl;

import eubrunoo07.projects.fleetmanagement.evidence_service.enums.EvidenceType;
import eubrunoo07.projects.fleetmanagement.evidence_service.model.BucketFile;
import eubrunoo07.projects.fleetmanagement.evidence_service.service.EvidenceService;
import io.minio.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket-name}")
    private String bucketName;

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png"
    );

    @Override
    public BucketFile uploadEvidence(MultipartFile file, Long tripId, String evidenceType) {
        validateFile(file);

        String extension = getExtension(file.getOriginalFilename());
        String fileName = buildFileName(tripId, EvidenceType.valueOf(evidenceType), extension);
        String objectPath = tripId + "/" + fileName;

        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectPath)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar arquivo no MinIO", e);
        }

        return new BucketFile(
                objectPath,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                LocalDateTime.now()
        );
    }
    @Override
    public byte[] getEvidence(Long tripId, EvidenceType type) {
        try {
            List<String> objects = listObjectsInTrip(tripId);
            String objectName = objects.stream()
                    .filter(obj -> obj.contains(type.name()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Nenhuma evidência encontrada"));

            try (GetObjectResponse response = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            )) {
                return response.readAllBytes();
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao baixar evidência", e);
        }
    }
    public List<String> listObjectsInTrip(Long tripId) {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix(tripId + "/")
                            .recursive(true)
                            .build()
            );

            List<String> files = new ArrayList<>();
            for (Result<Item> item : results) {
                files.add(item.get().objectName());
            }
            return files;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar evidências", e);
        }
    }
    private void validateFile(MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalArgumentException("Arquivo vazio");

        if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType()))
            throw new IllegalArgumentException("Somente imagens JPEG e PNG são permitidas");
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains("."))
            return "jpg"; // fallback

        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private String buildFileName(Long tripId, EvidenceType type, String extension) {
        String timestamp = LocalDateTime.now().toString()
                .replace(":", "-");

        return tripId + "-" + type.name() + "-" + timestamp + "." + extension;
    }
    public List<String> listByTrip(Long tripId) {
        try {
            Iterable<Result<Item>> items = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );

            List<String> result = new ArrayList<>();

            for (Result<Item> item : items) {
                String name = item.get().objectName();
                if (name.startsWith(tripId + "_")) {
                    result.add(name);
                }
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar arquivos", e);
        }
    }
}
