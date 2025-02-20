package loicMangele.Direct237_20.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationDTO(
        Long id,
        @NotNull(message = "Le nom du client est obligatoire")
        String nomClient,

        @NotNull(message = "Le numéro de téléphone du client est obligatoire")
        @Min(value = 1000000000, message = "Le numéro de téléphone doit être valide")
       String telephoneClient,


        @NotNull(message = "L'ID du voyage est obligatoire")
        Long voyageId,

        @NotBlank(message = "Specifiez le contenu du colis")
        String contenu,

        @Min(value = 1, message = "Ajoutez un poids valide supérieur à zéro")
        double poids,


        @NotBlank(message = "La ville de destination est obligatoire")
        String ville,

        String trackingNumber
) {}

