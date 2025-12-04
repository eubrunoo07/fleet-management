package eubrunoo07.projects.fleetmanagement.vehicle_service.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;
import eubrunoo07.projects.fleetmanagement.vehicle_service.service.UpdateVehicleStatusService;
import eubrunoo07.projects.fleetmanagement.vehicle_service.subscriber.representation.CanceledTripRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TripCanceledSubscriber {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UpdateVehicleStatusService service;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${fleet-management.config.kafka.topics.trip-canceled}"
    )
    public void listen(String json){
        try{
            var representation = objectMapper.readValue(json, CanceledTripRepresentation.class);
            service.updateStatus(representation.getVehicleId(), VehicleStatus.AVAILABLE);
        } catch (JsonProcessingException e) {
            log.error("Error on reading json of canceled trip");
        }
    }

}
