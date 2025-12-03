package eubrunoo07.projects.fleetmanagement.trip_service.publisher;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eubrunoo07.projects.fleetmanagement.trip_service.mapper.TripMapper;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TripStartedPublisher {

    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${fleet-management.config.kafka.topics.trip-started}")
    private String topic;

    public void publish(Trip trip){
        try{
            var representation = tripMapper.mapToRepresentation(trip);
            var json = objectMapper.writeValueAsString(representation);
            template.send(topic, "data", json);
        } catch (JsonProcessingException e) {
            log.error("Error while processing json to send", e);
        }
    }


}
