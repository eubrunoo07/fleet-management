package eubrunoo07.projects.fleetmanagement.driver_service.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.DriverStatus;
import eubrunoo07.projects.fleetmanagement.driver_service.service.UpdateDriverStatusService;
import eubrunoo07.projects.fleetmanagement.driver_service.subscriber.representation.TripFinishedRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TripFinishedSubscriber {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UpdateDriverStatusService service;

    @KafkaListener(
            groupId = "${${spring.kafka.consumer.group-id}",
            topics = "${fleet-management.config.kafka.topics.trip-ended}"
    )
    public void listener(String json){
        try{
            var representation = objectMapper.readValue(json, TripFinishedRepresentation.class);
            service.updateStatus(representation.getDriverId(), DriverStatus.AVAILABLE);
        } catch (JsonProcessingException e) {
            log.error("Error parsing json in finished trip");
        }
    }

}
