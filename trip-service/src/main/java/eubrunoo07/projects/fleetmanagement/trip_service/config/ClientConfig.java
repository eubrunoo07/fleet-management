package eubrunoo07.projects.fleetmanagement.trip_service.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "eubrunoo07.projects.fleetmanagement.trip_service.client")
public class ClientConfig {
}
