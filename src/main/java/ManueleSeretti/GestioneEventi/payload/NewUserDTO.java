package ManueleSeretti.GestioneEventi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record NewUserDTO(

        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String username,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email) {
}
// Il record definisce una classe IMMUTABILE (cioè non ho a disposizione dei setter per cambiare i valori degli oggetti)
