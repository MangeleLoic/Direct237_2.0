package loicMangele.Direct237_20.repositories;


import loicMangele.Direct237_20.entities.Client;
import loicMangele.Direct237_20.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClient(Client client);
    List<Reservation> findAllByVoyageId(Long voyageId);
    Optional<Reservation> findByTrackingNumber(String trackingNumber);

}
