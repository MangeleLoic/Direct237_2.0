package loicMangele.Direct237_20.repositories;


import loicMangele.Direct237_20.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByNomClientAndNumeroTelephone(String nomClient, String numeroTelephone);
}