package eubrunoo07.projects.fleetmanagement.evidence_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BucketFile {
    private String objectPath;
    private String fileName;
    private String contentType;
    private long size;
    private LocalDateTime uploadTime;
}
