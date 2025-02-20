package loicMangele.Direct237_20.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "voyages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate date_voyage;

    @Column(name = "Kiols_disponibles")
    private double kilosDisponibles;

    @Column(name = "kilos_reservées")
    private double kilosReservees;

    public LocalDate getDate_voyage() {
        return date_voyage;
    }

    public double getKilosDisponibles() {
        return kilosDisponibles;
    }

    public double getKilosReservees() {
        return kilosReservees;
    }

    public Long getId() {
        return id;
    }

    public void setDate_voyage(LocalDate date_voyage) {
        this.date_voyage = date_voyage;
    }

    public void setKilosDisponibles(double kilosDisponibles) {
        this.kilosDisponibles = kilosDisponibles;
    }

    public void setKilosReservees(double kilosReservees) {
        this.kilosReservees = kilosReservees;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

