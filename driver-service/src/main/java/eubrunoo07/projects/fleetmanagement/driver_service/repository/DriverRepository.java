package eubrunoo07.projects.fleetmanagement.driver_service.repository;

import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByCnhNumber(String cnhNumber);
}
