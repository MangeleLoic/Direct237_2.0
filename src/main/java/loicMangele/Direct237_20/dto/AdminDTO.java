package loicMangele.Direct237_20.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminDTO(@NotBlank(message = "Le nom est obligatoire")
                       @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
                       String nom,

                       @NotBlank(message = "L'email est obligatoire" )
                       @Email(message = "Le format de l'email n'est pas valide")
                       String email,

                       @NotBlank(message = "Le mot de passe est obligatoire")
                       String password)
{
}
