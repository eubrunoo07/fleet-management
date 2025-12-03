package eubrunoo07.projects.fleetmanagement.driver_service.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.DriverStatus;
import eubrunoo07.projects.fleetmanagement.driver_service.mapper.DriverMapper;
import eubrunoo07.projects.fleetmanagement.driver_service.service.UpdateDriverStatusService;
import eubrunoo07.projects.fleetmanagement.driver_service.subscriber.representation.TripStartedRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TripStartedSubscriber {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UpdateDriverStatusService service;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${fleet-management.config.kafka.topics.trip-started}"
    )
    public void listen(String json){
        try{
            var representation = objectMapper.readValue(json, TripStartedRepresentation.class);
            service.updateStatus(representation.getDriverId(), DriverStatus.TRAVELING);
            log.info("Mensagem recebida: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error parsing json to model");
        }
    }

}
