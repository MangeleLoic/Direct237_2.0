package loicMangele.Direct237_20.services;


import loicMangele.Direct237_20.entities.Voyage;
import loicMangele.Direct237_20.exceptions.VoyageNotFoundByIdException;
import loicMangele.Direct237_20.repositories.VoyageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepo voyageRepo;

    public Voyage createVoyage(Voyage voyage) {
        return voyageRepo.save(voyage);
    }

    public Voyage findVoyageById(Long id) {
        return voyageRepo.findById(id)
                .orElseThrow(() -> new VoyageNotFoundByIdException("Pas de voyages trouvés avec cet ID: " + id));
    }

    public Page<Voyage> getAllVoyages(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, sortBy));
        return voyageRepo.findAll(pageable);
    }

    public Voyage updateVoyage(Long id, Voyage updatedVoyage) {
        Voyage existingVoyage = findVoyageById(id);
        existingVoyage.setDate_voyage(updatedVoyage.getDate_voyage());
        existingVoyage.setKilosDisponibles(updatedVoyage.getKilosDisponibles());
        existingVoyage.setKilosReservees(updatedVoyage.getKilosReservees());
        return voyageRepo.save(existingVoyage);
    }

    public void deleteVoyage(Long id) {
        Voyage voyage = findVoyageById(id);
        voyageRepo.delete(voyage);
    }
}
