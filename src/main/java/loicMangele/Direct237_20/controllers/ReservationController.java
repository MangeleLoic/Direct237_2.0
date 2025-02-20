package loicMangele.Direct237_20.controllers;


import loicMangele.Direct237_20.dto.ReservationDTO;
import loicMangele.Direct237_20.entities.Reservation;
import loicMangele.Direct237_20.exceptions.ReservationNotFoundByIdException;
import loicMangele.Direct237_20.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @PostMapping
    public Reservation createReservation( @RequestBody ReservationDTO reservationDTO) {
        return reservationService.saveReservation(reservationDTO);
    }


    @GetMapping
    public Page<Reservation> getAllReservations(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "id") String sortBy) {
        return reservationService.findAllReservations(page, size, sortBy);
    }


    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.findById(id)
                .orElseThrow(() -> new ReservationNotFoundByIdException("Aucune réservation trouvée avec l'ID : " + id));
    }


    @GetMapping("/tracking/{trackingNumber}")
    public Reservation getReservationByTrackingNumber(@PathVariable String trackingNumber) {
        return reservationService.findByTrackingNumber(trackingNumber);
    }


    @GetMapping("/client/{clientId}")
    public List<Reservation> getReservationsByClient(@PathVariable Long clientId) {
        return reservationService.findByClient(clientId);
    }


    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id,
                                          @RequestBody ReservationDTO reservationDTO) {
        return reservationService.updateReservation(id, reservationDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.findByIdAndDelete(id);
    }
}
