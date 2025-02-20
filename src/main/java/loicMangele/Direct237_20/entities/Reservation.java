package loicMangele.Direct237_20.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomClient;

    @Column
    private String telephoneClient;

    @Column(nullable = false)
    private double poids;


    @Column(nullable = false)
    private String ville;

    @Column
    private String contenu;

    @Column(name = "code", unique = true)
    private String trackingNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voyage_id", nullable = false)
    private Voyage voyage;

    public Reservation(Long id, String nomClient, String telephoneClient, double poids, String ville, String contenu, String trackingNumber, Client client, Voyage voyage) {
        this.id = id;
        this.nomClient = nomClient;
        this.telephoneClient = telephoneClient;
        this.poids = poids;
        this.ville = ville;
        this.contenu = contenu;
        this.trackingNumber = trackingNumber;
        this.client = client;
        this.voyage = voyage;
    }

    public Reservation() {
    }


    public double getPoids() {
        return poids;
    }

    public String getVille() {
        return ville;
    }

    public String getContenu() {
        return contenu;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Client getClient() {
        return client;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public Long getId() {
        return id;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }
}
