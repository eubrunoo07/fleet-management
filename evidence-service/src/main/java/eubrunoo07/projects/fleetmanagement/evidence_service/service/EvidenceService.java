package eubrunoo07.projects.fleetmanagement.evidence_service.service;

import eubrunoo07.projects.fleetmanagement.evidence_service.enums.EvidenceType;
import eubrunoo07.projects.fleetmanagement.evidence_service.model.BucketFile;
import org.springframework.web.multipart.MultipartFile;

public interface EvidenceService {
    BucketFile uploadEvidence(MultipartFile file, Long tripId, String evidenceType);

    byte[] getEvidence(Long tripId, EvidenceType evidenceType);
}
