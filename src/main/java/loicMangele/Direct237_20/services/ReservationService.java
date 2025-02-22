package loicMangele.Direct237_20.services;


import loicMangele.Direct237_20.dto.ReservationDTO;
import loicMangele.Direct237_20.entities.Client;
import loicMangele.Direct237_20.entities.Reservation;
import loicMangele.Direct237_20.entities.Voyage;
import loicMangele.Direct237_20.exceptions.*;
import loicMangele.Direct237_20.repositories.ClientRepo;
import loicMangele.Direct237_20.repositories.ReservationRepo;
import loicMangele.Direct237_20.repositories.VoyageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private VoyageRepo voyageRepo;

    public Reservation saveReservation(ReservationDTO body) {

        reservationRepo.findByTrackingNumber(body.trackingNumber()).ifPresent(existingReservation -> {
            throw new BadRequestException("Une réservation avec le numéro de suivi " + body.trackingNumber() + " existe déjà.");
        });

        Optional<Client> optionalClient = clientRepo.findByNomClientAndNumeroTelephone(body.nomClient(), body.telephoneClient());
        Client client = optionalClient.orElseGet(() -> {
            Client newClient = new Client();
            newClient.setNomClient(body.nomClient());
            newClient.setNumeroTelephone(body.telephoneClient());
            return clientRepo.save(newClient);
        });

        Voyage voyage = voyageRepo.findById(body.voyageId())
                .orElseThrow(() -> new VoyageNotFoundByIdException("Voyage introuvable avec l'ID : " + body.voyageId()));


        if (body.poids() > voyage.getKilosDisponibles()) {
            throw new BadRequestException("Impossible de réserver : il ne reste que " + voyage.getKilosDisponibles() + " kg disponibles.");
        }


        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setNomClient(body.nomClient());
        reservation.setTelephoneClient(body.telephoneClient());
        reservation.setTrackingNumber(body.trackingNumber());
        reservation.setVoyage(voyage);
        reservation.setContenu(body.contenu());
        reservation.setPoids(body.poids());
        reservation.setVille(body.ville());


        voyage.setKilosReservees(voyage.getKilosReservees() + body.poids());
        voyage.setKilosDisponibles(voyage.getKilosDisponibles() - body.poids());

        voyageRepo.save(voyage);
        return reservationRepo.save(reservation);
    }


    public Page<Reservation> findAllReservations(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return reservationRepo.findAll(pageable);
    }


    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    }


    public List<Reservation> findByClient(Long clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundByIdException("Client introuvable avec l'ID : " + clientId));

        List<Reservation> reservations = reservationRepo.findByClient(client);
        if (reservations.isEmpty()) {
            throw new ReservationNotFoundByClientException("Aucune réservation trouvée pour le client avec l'ID : " + clientId);
        }
        return reservations;
    }


    public Reservation findByTrackingNumber(String trackingNumber) {
        return reservationRepo.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new ReservationNotFoundByTrackingException(trackingNumber));
    }


    public Reservation updateReservation(long id, ReservationDTO body) {
        return reservationRepo.findById(id).map(reservation -> {
            if (!reservation.getTrackingNumber().equals(body.trackingNumber()) &&
                    reservationRepo.findByTrackingNumber(body.trackingNumber()).isPresent()) {
                throw new BadRequestException("Une réservation avec le numéro de suivi " + body.trackingNumber() + " existe déjà.");
            }

            reservation.setContenu(body.contenu());
            reservation.setPoids(body.poids());
            reservation.setVille(body.ville());
            reservation.setTrackingNumber(body.trackingNumber());

            return reservationRepo.save(reservation);
        }).orElseThrow(() -> new ReservationNotFoundByIdException("Aucune réservation trouvée avec l'ID : " + id));
    }


    public void findByIdAndDelete(long id) {
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new ReservationNotFoundByIdException("Aucune réservation trouvée avec l'ID : " + id));

        reservationRepo.delete(reservation);
    }
}
