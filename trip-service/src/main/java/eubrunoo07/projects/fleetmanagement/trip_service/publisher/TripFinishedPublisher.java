package eubrunoo07.projects.fleetmanagement.trip_service.publisher;

import eubrunoo07.projects.fleetmanagement.trip_service.mapper.TripMapper;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
public class TripFinishedPublisher {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${fleet-management.config.kafka.topics.trip-ended}")
    private String topic;

    public void publish(Trip trip){
        try{
            var representation = tripMapper.mapToRepresentationFinished(trip);
            var json = objectMapper.writeValueAsString(representation);
            template.send(topic, "data", json);
        } catch (Exception e){
            log.error("Error in trip finished publisher");
        }
    }

}
