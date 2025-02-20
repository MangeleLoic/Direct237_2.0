package loicMangele.Direct237_20.repositories;


import loicMangele.Direct237_20.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByNom(String nom);
    Optional<Admin> findAdminByEmail(String email);
}
