package eubrunoo07.projects.fleetmanagement.vehicle_service.subscriber;

import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;
import eubrunoo07.projects.fleetmanagement.vehicle_service.service.UpdateVehicleStatusService;
import eubrunoo07.projects.fleetmanagement.vehicle_service.subscriber.representation.TripFinishedRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class TripFinishedSubscriber {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UpdateVehicleStatusService service;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${fleet-management.config.kafka.topics.trip-ended}"
    )
    public void listener(String json){
        try{
            var representation = objectMapper.readValue(json, TripFinishedRepresentation.class);
            service.updateStatus(representation.getVehicleId(), VehicleStatus.AVAILABLE);
        } catch (Exception e){
            log.error("Error to subscribe finished trip");
        }
    }

}
