package eubrunoo07.projects.fleetmanagement.evidence_service.controller;

import eubrunoo07.projects.fleetmanagement.evidence_service.enums.EvidenceType;
import eubrunoo07.projects.fleetmanagement.evidence_service.model.BucketFile;
import eubrunoo07.projects.fleetmanagement.evidence_service.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fleet/management/api/evidences")
public class EvidenceController {

    @Autowired
    private EvidenceService evidenceService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadEvidence(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tripId") Long tripId,
            @RequestParam("evidenceType") String evidenceType
    ) {
        BucketFile bucketFile = evidenceService.uploadEvidence(file, tripId, evidenceType);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<byte[]> getEvidence(
            @PathVariable Long tripId,
            @RequestParam("type") String evidenceType
    ) {
        byte[] file = evidenceService.getEvidence(tripId, EvidenceType.valueOf(evidenceType));
        return ResponseEntity.ok(file);
    }

}
